package com.ebank.controller;

import com.ebank.dto.AuthRequest;
import com.ebank.dto.AuthResponse;
import com.ebank.dto.ChangePasswordDTO;
import com.ebank.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO dto) {
        authService.changePassword(dto.getOldPassword(), dto.getNewPassword());
        return ResponseEntity.ok("Mot de passe modifié avec succès");
    }

}
