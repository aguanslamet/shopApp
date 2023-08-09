package com.shopApp.service.auth;

import com.shopApp.dto.request.UserRequest;
import com.shopApp.dto.response.JWTResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(UserRequest request);
    ResponseEntity<JWTResponse> login(UserRequest request);
}
