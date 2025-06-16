package com.ebank.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardDTO {
    private String numeroCompte;
    private double solde;
    private List<OperationDTO> operations;
}