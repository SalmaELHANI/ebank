package com.ebank.controller;

import com.ebank.dto.CompteDTO;
import com.ebank.dto.CompteRequestDTO;
import com.ebank.service.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compte")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @PostMapping("/creer")
    @PreAuthorize("hasAuthority('ROLE_AGENT_GUICHET')")
    public ResponseEntity<CompteDTO> creerCompte(@RequestBody @Valid CompteRequestDTO dto) {
        return ResponseEntity.ok(compteService.creerCompte(dto));
    }
}
