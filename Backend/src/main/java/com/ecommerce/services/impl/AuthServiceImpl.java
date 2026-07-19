package com.ecommerce.services.impl;

import com.ecommerce.dto.request.LoginRequest;
import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.dto.response.AuthResponse;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.enums.RoleType;
import com.ecommerce.event.UserRegisteredEvent;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AuthResponse register(RegisterRequest request) {

        log.info(
                "Entering method : AuthServiceImpl.register()"
        );

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        Role role = roleRepository
                .findByRoleName(RoleType.ROLE_CUSTOMER)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Default role not found"
                        ));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(role)
                .build();

        User savedUser =
                userRepository.save(user);

        log.info(
                "User registered successfully. User Id : {}",
                savedUser.getId()
        );

        eventPublisher.publishEvent(
                new UserRegisteredEvent(
                        savedUser
                )
        );

        log.info(
                "Exiting method : AuthServiceImpl.register()"
        );

        return AuthResponse.builder()
                .message(
                        "User registered successfully"
                )
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        log.info(
                "Entering method : AuthServiceImpl.login()"
        );

        User user =
                userRepository.findByEmail(
                        request.getEmail()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        ));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        log.info(
                "User logged in successfully. User Id : {}",
                user.getId()
        );

        log.info(
                "Exiting method : AuthServiceImpl.login()"
        );

        UserDetails userDetails =
                customUserDetailsService
                        .loadUserByUsername(
                                user.getEmail()
                        );

        String token =
                jwtService.generateToken(
                        userDetails
                );

        return AuthResponse.builder()
                .message("Login successful")
                .token(token)
                .build();
    }
}