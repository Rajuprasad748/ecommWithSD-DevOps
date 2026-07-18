package com.ecommerce.services;
import com.raju.ecommerce.dto.request.LoginRequest;
import com.raju.ecommerce.dto.request.RegisterRequest;
import com.raju.ecommerce.dto.response.AuthResponse;


public class AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
