package com.ecommerce.schedulers;

import com.ecommerce.entities.Order;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.repos.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 */30 * * * *")
    public void cancelExpiredOrders() {

        log.info(
                "Entering method : OrderScheduler.cancelExpiredOrders()"
        );

        List<Order> orders =
                orderRepository.findAll();

        for (Order order : orders) {

            if (order.getOrderStatus()
                    == OrderStatus.PENDING
                    &&
                    order.getOrderDate()
                            .isBefore(
                                    LocalDateTime.now()
                                            .minusMinutes(30)
                            )) {

                order.setOrderStatus(
                        OrderStatus.CANCELLED
                );

                orderRepository.save(order);

                log.info(
                        "Cancelled Order : {}",
                        order.getOrderNumber()
                );
            }
        }

        log.info(
                "Exiting method : OrderScheduler.cancelExpiredOrders()"
        );
    }
}