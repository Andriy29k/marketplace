package com.example.marketplace.services;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.User;
import com.example.marketplace.repositories.ProductRepository;
import com.example.marketplace.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Principal principal;

    @InjectMocks
    private ProductService productService;

    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("seller@test.com");

        testProduct = new Product();
        testProduct.setTitle("Test Product");
    }

    @Test
    void saveProduct_ShouldSaveProductWithImages() throws IOException {
        // Arrange
        when(principal.getName()).thenReturn(testUser.getEmail());
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(testUser);

        MockMultipartFile file1 = new MockMultipartFile("file1", "img1.jpg", "image/jpeg", "content".getBytes());
        MockMultipartFile emptyFile = new MockMultipartFile("file2", "", "image/jpeg", new byte[0]);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            if (p.getImages() != null && !p.getImages().isEmpty()) {
                p.getImages().get(0).setId(100L);
            }
            return p;
        });

        // Act
        productService.saveProduct(principal, testProduct, file1, emptyFile, emptyFile);

        // Assert
        assertEquals(testUser, testProduct.getUser());
        assertEquals(1, testProduct.getImages().size());
        assertTrue(testProduct.getImages().get(0).isPreviewImage());
        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getTitle());
    }

    @Test
    void getUserByPrincipal_ShouldReturnEmptyUser_WhenPrincipalIsNull() {
        User result = productService.getUserByPrincipal(null);

        assertNotNull(result);
        assertNull(result.getEmail());
    }
}
