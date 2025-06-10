package com.ebank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    private LocalDateTime date;

    @ManyToOne
    private Compte compteSource;

    @ManyToOne
    private Compte compteDestination;
}

