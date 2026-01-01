package com.khalil.ebank.dtos.user;

import com.khalil.ebank.enums.RoleName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String email;
    private RoleName role;
    private boolean enabled;
    private LocalDateTime createdAt;
}
