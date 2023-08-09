package com.shopApp.service.auth.impl;

import com.shopApp.dto.request.UserRequest;
import com.shopApp.dto.response.DefaultResponse;
import com.shopApp.dto.response.JWTResponse;
import com.shopApp.dto.response.UserDetailsDto;
import com.shopApp.model.Users;
import com.shopApp.repository.UserRepository;
import com.shopApp.service.auth.AuthService;
import com.shopApp.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<?> register(UserRequest request) {
        boolean userExsist = userRepository.findByEmail(request.getEmail()).isPresent();
        if (userExsist){
            throw new RuntimeException( String.format("User with Email '%s' already",request.getEmail()));
        }
        Users users = new Users();
        String encode = bCryptPasswordEncoder.encode(request.getPassword());
        users.setPassword(encode);
        users.setEmail(request.getEmail());
        users.setUsername(request.getUsername());
        userRepository.save(users);

        return new ResponseEntity<>(
                DefaultResponse.builder()
                        .statusCode(200)
                        .message("Success")
                        .build(),
                HttpStatus.OK);
    }


    public ResponseEntity<JWTResponse> login(UserRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsDto userDetails = (UserDetailsDto) authentication.getPrincipal();

        return ResponseEntity.ok(new JWTResponse(jwt,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail()));
    }

}
