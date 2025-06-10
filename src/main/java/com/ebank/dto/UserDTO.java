package com.ebank.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String nom;
    private String prenom;
    private String email;
    private String role;
}

