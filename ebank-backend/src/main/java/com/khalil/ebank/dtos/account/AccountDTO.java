package com.khalil.ebank.dtos.account;

import com.khalil.ebank.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountDTO {
    private Long id;
    private String rib;
    private BigDecimal solde;
    private AccountStatus status;
    private String clientName; // Simplified client info
    private LocalDateTime createdAt;
}
