package com.ecommerce.listeners;
import com.ecommerce.events.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegistrationListener {

    @Async
    @EventListener
    public void handleUserRegisteredEvent(
            UserRegisteredEvent event) {

        log.info(
                "Entering method : UserRegistrationListener.handleUserRegisteredEvent()"
        );

        Long userId = event.getUser().getId();

        String email = event.getUser().getEmail();

        log.info(
                "New user registered successfully | userId={} | email={}",
                userId,
                email
        );

        // Future Tasks
        // sendWelcomeEmail(event.getUser());
        // createDefaultCart(event.getUser());
        // createAuditLog(event.getUser());

        log.info(
                "Exiting method : UserRegistrationListener.handleUserRegisteredEvent()"
        );
    }

}
