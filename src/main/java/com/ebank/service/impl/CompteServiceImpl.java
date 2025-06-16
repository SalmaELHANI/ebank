package com.ebank.service.impl;
import com.ebank.exception.BadRequestException;

import com.ebank.dto.CompteDTO;
import com.ebank.dto.CompteRequestDTO;
import com.ebank.entity.Client;
import com.ebank.entity.Compte;
import com.ebank.entity.StatutCompte;
import com.ebank.entity.TypeCompte;
import com.ebank.exception.ResourceNotFoundException;
import com.ebank.mapper.CompteMapper;
import com.ebank.repository.ClientRepository;
import com.ebank.repository.CompteRepository;
import com.ebank.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    @Override
    public CompteDTO creerCompte(CompteRequestDTO dto) {
        // Vérifier si le client existe
        Client client = clientRepository.findAll().stream()
                .filter(c -> c.getUser().getLogin().equals(dto.getNumeroIdentite()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(" Client introuvable avec ce numéro d'identité"));


        if (!dto.getRib().matches("\\d{24}")) {
            throw new BadRequestException(" RIB invalide : il doit contenir exactement 24 chiffres.");
        }

        if (compteRepository.findByNumero(dto.getRib()).isPresent()) {
            throw new BadRequestException("Un compte avec ce RIB existe déjà.");
        }



        Compte compte = Compte.builder()
                .numero(dto.getRib())
                .solde(0.0)
                .type(TypeCompte.COURANT)
                .client(client)
                .statut(StatutCompte.OUVERT)
                .build();

        compteRepository.save(compte);

        return CompteMapper.toCompteDTO(compte);
    }
}