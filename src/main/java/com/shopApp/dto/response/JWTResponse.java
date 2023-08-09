package com.shopApp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTResponse {
    private String JWT;
    private Long id;
    private String username;
    private String email;

    public JWTResponse(String jwt, Long id, String username, String email) {
        this.JWT = jwt;
        this.id = id;
        this.username = username;
        this.email= email;
    }
}
