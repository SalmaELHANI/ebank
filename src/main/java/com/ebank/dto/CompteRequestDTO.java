package com.ebank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompteRequestDTO {

    @NotBlank(message = "Le RIB est obligatoire")
    private String rib;

    @NotBlank(message = "Le numéro d'identité du client est obligatoire")
    private String numeroIdentite;


}