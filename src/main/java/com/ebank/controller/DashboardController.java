package com.ebank.controller;

import com.ebank.dto.DashboardDTO;
import com.ebank.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/comptes")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<String>> getComptes(Principal principal) {
        return ResponseEntity.ok(dashboardService.getComptesDuClient(principal.getName()));
    }

    @GetMapping("/infos")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<DashboardDTO> getDashboard(@RequestParam String numeroCompte, Principal principal) {
        return ResponseEntity.ok(dashboardService.getDashboardForCompte(principal.getName(), numeroCompte));
    }
}
