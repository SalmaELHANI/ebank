package com.ebank.controller;

import com.ebank.dto.VirementRequestDTO;
import com.ebank.service.VirementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/virement")
@RequiredArgsConstructor
public class VirementController {

    private final VirementService virementService;

    @PostMapping("/effectuer")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> effectuer(@RequestBody @Valid VirementRequestDTO dto, Principal principal) {
        virementService.effectuerVirement(principal.getName(), dto);
        return ResponseEntity.ok("Virement effectué avec succès");
    }
}
