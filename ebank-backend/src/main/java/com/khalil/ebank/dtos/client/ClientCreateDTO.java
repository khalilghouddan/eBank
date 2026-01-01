package com.khalil.ebank.dtos.client;

import com.khalil.ebank.dtos.user.UserCreateDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreateDTO extends UserCreateDTO {
    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    private String identiteNum; // cin obligatoire

    @NotNull
    private String adressePostale;

    @NotNull
    private LocalDate dateNaissance;
}
