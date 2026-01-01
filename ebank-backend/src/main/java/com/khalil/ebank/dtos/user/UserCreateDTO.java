package com.khalil.ebank.dtos.user;

import com.khalil.ebank.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateDTO {
    @NotNull
    private String login;

    private String password;

    @Email
    @NotNull
    private String email;

    // Optional: if admin creates user, they can assign role. Default might be
    // CLIENT.
    private RoleName role;
}
