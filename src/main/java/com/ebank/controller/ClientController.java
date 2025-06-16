package com.ebank.controller;

import com.ebank.dto.ClientDTO;
import com.ebank.dto.ClientRequestDTO;
import com.ebank.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/ajouter")
    @PreAuthorize("hasAuthority('ROLE_AGENT_GUICHET')")
    public ResponseEntity<ClientDTO> ajouterClient(@RequestBody @Valid ClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.ajouterClient(dto));
    }
}
