package com.shopApp.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class UserRequest {
    private Long id;
    private String username;
    @Email(message = "In Correte Formate Email!")
    private String email;
    @NotEmpty(message = "Password is Required!")
    private String password;
}
