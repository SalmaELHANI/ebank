package com.ebank.mapper;

import com.ebank.dto.VirementResponseDTO;
import com.ebank.entity.Virement;

public class VirementMapper {
    public static VirementResponseDTO toVirementResponseDTO(Virement virement) {
        VirementResponseDTO dto = new VirementResponseDTO();
        dto.setId(virement.getId());
        dto.setMontant(virement.getMontant());
        dto.setDate(virement.getDate());
        dto.setNumeroSource(virement.getCompteSource().getNumero());
        dto.setNumeroDestination(virement.getCompteDestination().getNumero());
        return dto;
    }
}
