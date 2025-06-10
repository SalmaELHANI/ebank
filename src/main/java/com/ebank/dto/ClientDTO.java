package com.ebank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
}
