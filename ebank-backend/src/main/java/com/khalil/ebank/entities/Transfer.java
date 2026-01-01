package com.khalil.ebank.entities;

import com.khalil.ebank.enums.TransferStatus;
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
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account sourceAccount;

    @ManyToOne(optional = false)
    private Account destAccount;

    @Column(nullable = false)
    private BigDecimal montant;
    // combien on envoi

    private String motif;

    private LocalDateTime executedAt;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @ManyToOne
    private User createdBy;
}
