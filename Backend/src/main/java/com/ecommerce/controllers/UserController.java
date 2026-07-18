package com.ecommerce.controllers;
import com.ecommerce.dto.request.UpdateUserRequest;
import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile() {

        log.info(
                "Entering method : UserController.getProfile()"
        );

        UserResponse response =
                userService.getProfile();

        log.info(
                "Exiting method : UserController.getProfile()"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long userId) {

        log.info(
                "Entering method : UserController.getUserById() | userId={}",
                userId
        );

        UserResponse response =
                userService.getUserById(userId);

        log.info(
                "Exiting method : UserController.getUserById()"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        log.info(
                "Entering method : UserController.getAllUsers()"
        );

        List<UserResponse> users =
                userService.getAllUsers();

        log.info(
                "Exiting method : UserController.getAllUsers()"
        );

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request) {

        log.info(
                "Entering method : UserController.updateUser() | userId={}",
                userId
        );

        UserResponse response =
                userService.updateUser(userId, request);

        log.info(
                "Exiting method : UserController.updateUser()"
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {

        log.info(
                "Entering method : UserController.deleteUser() | userId={}",
                userId
        );

        userService.deleteUser(userId);

        log.info(
                "Exiting method : UserController.deleteUser()"
        );

        return ResponseEntity.noContent().build();
    }
}
