package com.shopApp.controller.auth;

import com.shopApp.dto.request.UserRequest;
import com.shopApp.dto.response.JWTResponse;
import com.shopApp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest request){
        return authService.register(request);
    }
    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@Valid @RequestBody UserRequest request){
        return authService.login(request);
    }

}
