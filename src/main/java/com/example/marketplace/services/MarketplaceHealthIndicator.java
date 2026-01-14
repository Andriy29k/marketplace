package com.example.marketplace.services;

import com.example.marketplace.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketplaceHealthIndicator implements HealthIndicator {

    private final ProductRepository productRepository;

    @Override
    public Health health() {
        long productCount = productRepository.count();

        if (productCount >= 0) {
            return Health.up()
                    .withDetail("totalProducts", productCount)
                    .withDetail("marketplaceStatus", "Operational")
                    .build();
        }
        return Health.down()
                .withDetail("error", "Could not count products")
                .build();
    }
}
