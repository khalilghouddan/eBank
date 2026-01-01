package com.khalil.ebank.dtos.operation;

import com.khalil.ebank.enums.OperationType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperationDTO {
    private Long id;
    private OperationType type;
    private BigDecimal montant;
    private LocalDateTime date;
    private String libelle;
    private Long accountId; // Reference to account
}
