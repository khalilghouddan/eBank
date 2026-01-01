package com.khalil.ebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Client extends User {

    private String nom;
    private String prenom;

    // RG_4
    @Column(unique = true, nullable = false)
    private String identiteNum; // cin ou autre, faut que ca soit unique sinon c la merde

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String adressePostale;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Account> accounts;

    // Note: User relationship is handled by Inheritance (User fields are part of
    // Client)
    // herite de user donc pas besoin de refaire les champs login etc

}
