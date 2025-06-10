package com.ebank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Le login est obligatoire")
    private String login;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}