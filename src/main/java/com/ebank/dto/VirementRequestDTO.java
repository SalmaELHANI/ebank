package com.ebank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VirementRequestDTO {
    @NotBlank(message = "Le numéro du compte source est requis")
    private String numeroSource;

    @NotBlank(message = "Le numéro du compte destination est requis")
    private String numeroDestination;

    @DecimalMin(value = "1.0", message = "Le montant minimum est 1 MAD")
    private double montant;
}
