package com.ecommerce.schedulers;

import com.ecommerce.entities.Product;
import com.ecommerce.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryScheduler {

    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void checkLowInventory() {

        log.info(
                "Entering method : InventoryScheduler.checkLowInventory()"
        );

        List<Product> products =
                productRepository.findAll();

        for (Product product : products) {

            if (product.getStock() < 10) {

                log.warn(
                        "Low Stock Alert | Product={} | Stock={}",
                        product.getName(),
                        product.getStock()
                );
            }
        }

        log.info(
                "Exiting method : InventoryScheduler.checkLowInventory()"
        );
    }
}