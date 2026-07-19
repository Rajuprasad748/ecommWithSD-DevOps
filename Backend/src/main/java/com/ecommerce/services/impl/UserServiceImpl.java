package com.ecommerce.services.impl;

import com.ecommerce.dto.request.UpdateUserRequest;
import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.entities.User;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.repos.UserRepository;
import com.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponse getProfile(
            Long userId) {

        log.info(
                "Entering method : UserServiceImpl.getProfile()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        log.info(
                "Exiting method : UserServiceImpl.getProfile()"
        );

        return userMapper.toResponse(
                user
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(
            Long userId) {

        log.info(
                "Entering method : UserServiceImpl.getUserById()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        log.info(
                "Exiting method : UserServiceImpl.getUserById()"
        );

        return userMapper.toResponse(
                user
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        log.info(
                "Entering method : UserServiceImpl.getAllUsers()"
        );

        List<UserResponse> users =
                userRepository.findAll()
                        .stream()
                        .map(userMapper::toResponse)
                        .toList();

        log.info(
                "Exiting method : UserServiceImpl.getAllUsers()"
        );

        return users;
    }

    @Override
    public UserResponse updateUser(
            Long userId,
            UpdateUserRequest request) {

        log.info(
                "Entering method : UserServiceImpl.updateUser()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPhoneNumber(
                request.getPhoneNumber()
        );

        User updatedUser =
                userRepository.save(user);

        log.info(
                "User updated successfully. User Id : {}",
                updatedUser.getId()
        );

        log.info(
                "Exiting method : UserServiceImpl.updateUser()"
        );

        return userMapper.toResponse(
                updatedUser
        );
    }

    @Override
    public void deleteUser(
            Long userId) {

        log.info(
                "Entering method : UserServiceImpl.deleteUser()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        userRepository.delete(user);

        log.info(
                "User deleted successfully. User Id : {}",
                userId
        );

        log.info(
                "Exiting method : UserServiceImpl.deleteUser()"
        );
    }
}