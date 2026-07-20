package com.ecommerce.security;

import com.ecommerce.entities.User;
import com.ecommerce.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            @NonNull String email)
            throws UsernameNotFoundException {

        log.info(
                "Entering method : CustomUserDetailsService.loadUserByUsername()"
        );

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found"
                                ));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(
                        user.getEmail()
                )
                .password(
                        user.getPassword()
                )
                .authorities(
                        user.getRole()
                                .getRoleName()
//                                .name()
                )
                .build();
    }
}