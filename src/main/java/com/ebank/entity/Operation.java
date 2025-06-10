package com.ebank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TypeOperation type; // RETRAIT ou VERSEMENT

    @ManyToOne
    private Compte compte;
}
