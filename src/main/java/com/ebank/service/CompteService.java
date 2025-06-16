package com.ebank.service;

import com.ebank.dto.CompteDTO;
import com.ebank.dto.CompteRequestDTO;

public interface CompteService {
    CompteDTO creerCompte(CompteRequestDTO dto);

}