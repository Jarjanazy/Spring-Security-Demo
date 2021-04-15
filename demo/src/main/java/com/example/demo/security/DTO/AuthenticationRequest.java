package com.example.demo.security.DTO;

import lombok.*;

import java.io.Serializable;

@Data
public class AuthenticationRequest implements Serializable {
    private final String userName;
    private final String password;
}

