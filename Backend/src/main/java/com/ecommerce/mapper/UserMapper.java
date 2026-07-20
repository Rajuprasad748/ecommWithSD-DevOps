package com.ecommerce.mapper;

import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(
                        user.getRole() != null
                                ? user.getRole().getRoleName()
                                : null
                )
                .build();
    }
}