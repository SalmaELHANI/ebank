package com.ebank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String nom;

    private String prenom;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN ou CLIENT
}
