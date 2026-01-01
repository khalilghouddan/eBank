package com.khalil.ebank.entities;

import com.khalil.ebank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RG_9
    @Column(unique = true, nullable = false)
    private String rib;

    @ManyToOne(optional = false)
    private Client client;

    @Column(nullable = false)
    private BigDecimal solde; // RG_10: default 0 handled in logic or DB, but here type is BigDecimal
    // le fric !! attention bigdecimal important sinon on perd des centimes

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.solde == null)
            this.solde = BigDecimal.ZERO;
        if (this.status == null)
            this.status = AccountStatus.OPEN; // RG_10
        // ouvert par defaut, logique
    }
}
