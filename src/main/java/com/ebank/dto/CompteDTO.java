package com.ebank.dto;

import lombok.Data;

@Data
public class CompteDTO {
    private Long id;
    private String numero;
    private double solde;
    private String type;
    private Long clientId;
}

