package com.ebank.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;
}

