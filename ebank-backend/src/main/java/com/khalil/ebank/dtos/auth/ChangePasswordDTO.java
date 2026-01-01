package com.khalil.ebank.dtos.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordDTO {
    @NotNull
    private String currentPassword;

    @NotNull
    private String newPassword;
}
