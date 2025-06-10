package com.ebank.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;
    private String nom;
}