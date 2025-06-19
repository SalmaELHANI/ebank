package com.ebank.service.impl;

import com.ebank.dto.AuthRequest;
import com.ebank.dto.AuthResponse;
import com.ebank.entity.User;
import com.ebank.exception.IncorrectOldPasswordException;
import com.ebank.exception.InvalidCredentialsException;
import com.ebank.exception.ResourceNotFoundException;
import com.ebank.repository.UserRepository;
import com.ebank.security.JwtUtil;
import com.ebank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new InvalidCredentialsException("Login ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Login ou mot de passe incorrect");
        }
        String token = jwtUtil.generateToken(user.getLogin());

        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .nom(user.getNom())
                .build();
    }


    public void changePassword(String oldPassword, String newPassword) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new InvalidCredentialsException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IncorrectOldPasswordException("Ancien mot de passe incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}