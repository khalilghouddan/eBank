package com.khalil.ebank.dtos.transfer;

import com.khalil.ebank.validation.ValidRib;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {

    @NotNull
    @ValidRib
    private String sourceAccountRib; // rib source, faut verif si valide

    @NotNull
    @ValidRib
    private String destinationAccountRib;

    @NotNull
    @Min(value = 1, message = "Amount must be at least 1")
    private BigDecimal amount; // pas de virement de 0 dh lol

    private String description;
}
