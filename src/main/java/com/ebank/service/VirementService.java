package com.ebank.service;

import com.ebank.dto.VirementRequestDTO;

public interface VirementService {
    void effectuerVirement(String login, VirementRequestDTO dto);
}
