package com.ecommerce.listeners;
import com.ecommerce.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {
    @EventListener
    public void handleOrderPlacedEvent(
            OrderPlacedEvent event) {

        log.info(
                "Entering method : OrderListener.handleOrderPlacedEvent()"
        );

        Long orderId = event.getOrder().getId();

        log.info(
                "Order placed successfully. Order Id : {}",
                orderId
        );

        // Future Tasks
        // sendOrderConfirmationEmail();
        // generateInvoice();
        // updateInventory();
        // createAuditLog();

        log.info(
                "Exiting method : OrderListener.handleOrderPlacedEvent()"
        );
    }
}
