package com.ebank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientRequestDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^(\\+212|0)([5-7])\\d{8}$", message = "Numéro de téléphone invalide")
    private String telephone;
}
