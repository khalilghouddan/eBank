package com.khalil.ebank.dtos.client;

import com.khalil.ebank.dtos.account.AccountDTO;
import com.khalil.ebank.dtos.user.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDTO extends UserDTO {
    private String nom;
    private String prenom;
    private String identiteNum;
    private String adressePostale;
    private LocalDate dateNaissance;
    private List<AccountDTO> accounts;
}
