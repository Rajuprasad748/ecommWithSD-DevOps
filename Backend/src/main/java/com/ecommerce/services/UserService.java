package com.ecommerce.services;

import com.ecommerce.dto.request.UpdateUserRequest;
import com.ecommerce.dto.response.UserResponse;

import java.util.List;

public class UserService {

    UserResponse getProfile();

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(
            Long userId,
            UpdateUserRequest request
    );

    void deleteUser(Long userId);

}
