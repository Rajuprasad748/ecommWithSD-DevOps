package com.ecommerce.events;

import com.ecommerce.entities.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderPlacedEvent extends ApplicationEvent {

    private final Order order;

    public OrderPlacedEvent(Order order) {
        super(order);
        this.order = order;
    }
}
