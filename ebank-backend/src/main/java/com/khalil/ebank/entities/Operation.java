package com.khalil.ebank.entities;

import com.khalil.ebank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    @ManyToOne(optional = false)
    private Account account;

    // RG_15: Relie les deux operations d'un meme virement
    // lien vers le transfert parent, pr savoir d'ou ca vient
    @ManyToOne
    private Transfer transfer;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private String libelle;

    @Column(nullable = false)
    private BigDecimal montant;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
