package com.ebank.service.impl;

import com.ebank.dto.VirementRequestDTO;
import com.ebank.entity.*;
import com.ebank.exception.ResourceNotFoundException;
import com.ebank.repository.CompteRepository;
import com.ebank.repository.OperationRepository;
import com.ebank.repository.VirementRepository;
import com.ebank.service.VirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ebank.exception.CompteNotFoundException;
import com.ebank.exception.SoldeInsuffisantException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VirementServiceImpl implements VirementService {

    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;
    private final VirementRepository virementRepository;

    @Override
    public void effectuerVirement(String login, VirementRequestDTO dto) {
        Compte source = compteRepository.findByNumero(dto.getNumeroSource())
                .orElseThrow(() -> new ResourceNotFoundException("Compte source introuvable"));

        Compte destination = compteRepository.findByNumero(dto.getNumeroDestination())
                .orElseThrow(() -> new CompteNotFoundException("Compte destinataire introuvable"));

        if (source.getSolde() < dto.getMontant()) {
            throw new SoldeInsuffisantException("Solde insuffisant pour effectuer le virement");
        }

        // RG_13 : Débit du compte source
        source.setSolde(source.getSolde() - dto.getMontant());

        // RG_14 : Crédit du compte destinataire
        destination.setSolde(destination.getSolde() + dto.getMontant());

        // Sauvegarder les comptes
        compteRepository.save(source);
        compteRepository.save(destination);

        // RG_15 : Tracer les 2 opérations
        Operation debit = Operation.builder()
                .compte(source)
                .montant(dto.getMontant())
                .date(LocalDateTime.now())
                .type(TypeOperation.RETRAIT)
                .motif("Virement vers " + dto.getNumeroDestination() + " : " + dto.getMotif()) // ✅ contient "vers"
                .build();

        Operation credit = Operation.builder()
                .compte(destination)
                .montant(dto.getMontant())
                .date(LocalDateTime.now())
                .type(TypeOperation.VERSEMENT)
                .motif("Virement reçu de " + dto.getNumeroSource() + " : " + dto.getMotif()) // ✅ contient "reçu"
                .build();

        operationRepository.save(debit);
        operationRepository.save(credit);

        // Tracer dans table Virement si nécessaire
        Virement virement = Virement.builder()
                .compteSource(source)
                .compteDestination(destination)
                .montant(dto.getMontant())
                .date(LocalDateTime.now())
                .motif(dto.getMotif())
                .build();

        virementRepository.save(virement);
    }
}
