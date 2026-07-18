package com.ecommerce.events;
import com.ecommerce.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class UserRegisteredEvent {
    @Getter
    public class UserRegisteredEvent extends ApplicationEvent {

        private final User user;

        public UserRegisteredEvent(User user) {
            super(user);
            this.user = user;
        }
    }
}
