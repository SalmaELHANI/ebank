package com.ebank.service.impl;

import com.ebank.dto.ClientDTO;
import com.ebank.dto.ClientRequestDTO;
import com.ebank.entity.Client;
import com.ebank.entity.Role;
import com.ebank.entity.User;
import com.ebank.exception.ResourceNotFoundException;
import com.ebank.mapper.ClientMapper;
import com.ebank.repository.ClientRepository;
import com.ebank.repository.UserRepository;
import com.ebank.service.ClientService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Override
    public ClientDTO ajouterClient(ClientRequestDTO dto) {
        // RG_4: Vérifier unicité du numéro identité (login)
        if (userRepository.findByLogin(dto.getNumeroIdentite()).isPresent()) {
            throw new IllegalArgumentException("Le numéro d'identité existe déjà");
        }

        // RG_6: Vérifier unicité de l'email
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("L'email existe déjà");
        }

        // Générer mot de passe aléatoire
        String password = genererMotDePasse();

        // Créer User
        User user = User.builder()
                .login(dto.getNumeroIdentite())
                .password(passwordEncoder.encode(password))
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);

        // Créer Client
        Client client = ClientMapper.fromClientRequestDTO(dto, user);
        clientRepository.save(client);

        // RG_7: Envoyer email
        envoyerEmailLoginMotDePasse(dto.getEmail(), user.getLogin(), password);

        return ClientMapper.toClientDTO(client);
    }

    private String genererMotDePasse() {
        return new Random().ints(8, 33, 122)
                .mapToObj(i -> String.valueOf((char) i))
                .reduce("", String::concat);
    }

    private void envoyerEmailLoginMotDePasse(String to, String login, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Vos identifiants eBank");
        message.setText("Bonjour,\n\nVotre compte a été créé avec succès.\nLogin : " + login + "\nMot de passe : " + password);
        mailSender.send(message);
    }

}
