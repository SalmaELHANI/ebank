package com.ebank.service.impl;

import com.ebank.dto.DashboardDTO;
import com.ebank.dto.OperationDTO;
import com.ebank.entity.Client;
import com.ebank.entity.Compte;
import com.ebank.entity.Operation;
import com.ebank.mapper.OperationMapper;
import com.ebank.repository.ClientRepository;
import com.ebank.repository.CompteRepository;
import com.ebank.repository.OperationRepository;
import com.ebank.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    @Override
    public DashboardDTO getDashboardForCompte(String login, String numeroCompte) {
        Client client = clientRepository.findAll().stream()
                .filter(c -> c.getUser().getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Compte compte = client.getComptes().stream()
                .filter(c -> c.getNumero().equals(numeroCompte))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Compte introuvable pour ce client"));

        List<Operation> operations = operationRepository.findByCompteOrderByDateDesc(compte, PageRequest.of(0, 10));

        DashboardDTO dto = new DashboardDTO();
        dto.setNumeroCompte(compte.getNumero());
        dto.setSolde(compte.getSolde());
        dto.setOperations(
                operations.stream().map(OperationMapper::toOperationDTO).collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public List<String> getComptesDuClient(String login) {
        Client client = clientRepository.findAll().stream()
                .filter(c -> c.getUser().getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        return client.getComptes().stream()
                .sorted(Comparator.comparing(Compte::getId))
                .map(Compte::getNumero)
                .collect(Collectors.toList());
    }
}
