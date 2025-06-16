package com.ebank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresse;

    private String telephone;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateAnniversaire;



    @OneToOne
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;
}

