package com.ebank.mapper;

import com.ebank.dto.OperationDTO;
import com.ebank.entity.Operation;

import java.util.stream.Collectors;
public class OperationMapper {
    public static OperationDTO toOperationDTO(Operation operation) {
        OperationDTO dto = new OperationDTO();
        dto.setId(operation.getId());
        dto.setMontant(operation.getMontant());
        dto.setDate(operation.getDate());
        dto.setType(operation.getType().name());
        dto.setNumeroCompte(operation.getCompte().getNumero());
        return dto;
    }
}
