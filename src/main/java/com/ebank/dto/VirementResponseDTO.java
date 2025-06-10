package com.ebank.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VirementResponseDTO {
    private Long id;
    private String numeroSource;
    private String numeroDestination;
    private double montant;
    private LocalDateTime date;
}
