package com.khalil.ebank.dtos.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    @NotNull(message = "Login is required")
    private String login;

    @NotNull(message = "Password is required")
    private String password;
}
