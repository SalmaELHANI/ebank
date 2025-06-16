package com.ebank.service;

import java.util.Optional;
import com.ebank.dto.ClientDTO;
import com.ebank.dto.ClientRequestDTO;
import com.ebank.dto.DashboardDTO;

public interface ClientService {
    ClientDTO ajouterClient(ClientRequestDTO dto);

}
