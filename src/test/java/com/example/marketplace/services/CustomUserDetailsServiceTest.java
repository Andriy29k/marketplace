package com.example.marketplace.services;

import com.example.marketplace.models.User;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.services.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    @DisplayName("Should return UserDetails when user exists")
    void loadUserByUsername_Success() {
        // Arrange
        String email = "test@example.com";
        User mockUser = new User(email, "password123");
        mockUser.setActive(true);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        // Act
        UserDetails result = userDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getUsername());
        assertTrue(result.isEnabled(), "User should be active");
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return null when user not found")
    void loadUserByUsername_UserNotFound() {
        // Arrange
        String email = "missing@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act
        UserDetails result = userDetailsService.loadUserByUsername(email);

        // Assert
        assertNull(result, "Should return null if repository returns null");
        verify(userRepository, times(1)).findByEmail(email);
    }
}