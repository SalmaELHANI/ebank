package com.ebank.service;

import com.ebank.dto.DashboardDTO;
import java.util.List;

public interface DashboardService {
    DashboardDTO getDashboardForCompte(String login, String numeroCompte);
    List<String> getComptesDuClient(String login);
}