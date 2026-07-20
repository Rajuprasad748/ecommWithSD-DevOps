package com.ecommerce.security;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import org.jspecify.annotations.NonNull;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            @NonNull HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.AuthenticationException ex)
            throws IOException, ServletException {

        response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized"
        );
    }
}