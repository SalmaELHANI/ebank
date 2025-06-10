package com.ebank.mapper;

import com.ebank.dto.CompteDTO;
import com.ebank.entity.Compte;

public class CompteMapper {
    public static CompteDTO toCompteDTO(Compte compte) {
        CompteDTO dto = new CompteDTO();
        dto.setId(compte.getId());
        dto.setNumero(compte.getNumero());
        dto.setSolde(compte.getSolde());
        dto.setType(compte.getType().name());
        dto.setClientId(compte.getClient().getId());
        return dto;
    }
}
