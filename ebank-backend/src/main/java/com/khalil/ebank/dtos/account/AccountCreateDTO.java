package com.khalil.ebank.dtos.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountCreateDTO {
    @NotNull
    private Long clientId;

    private BigDecimal initialBalance;

    // Could add account type if needed later
}
