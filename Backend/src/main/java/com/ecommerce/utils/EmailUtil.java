package com.ecommerce.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final JavaMailSender
            mailSender;

    public void sendEmail(
            String to,
            String subject,
            String body) {

        log.info(
                "Entering method : EmailUtil.sendEmail()"
        );

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(to);

        message.setSubject(subject);

        message.setText(body);

        mailSender.send(message);

        log.info(
                "Email sent successfully to : {}",
                to
        );

        log.info(
                "Exiting method : EmailUtil.sendEmail()"
        );
    }
}