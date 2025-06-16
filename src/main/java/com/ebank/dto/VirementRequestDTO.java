package com.ebank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VirementRequestDTO {
    @NotBlank(message = "Le compte source est requis")
    private String numeroSource;

    @NotBlank(message = "Le compte destinataire est requis")
    private String numeroDestination;

    @DecimalMin(value = "1.0", message = "Le montant doit être supérieur à zéro")
    private double montant;

    private String motif;
}