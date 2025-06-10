package com.ebank.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperationDTO {
    private Long id;
    private double montant;
    private LocalDateTime date;
    private String type;
    private String numeroCompte;
}

