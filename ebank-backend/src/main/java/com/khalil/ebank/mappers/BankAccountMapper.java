package com.khalil.ebank.mappers;

import com.khalil.ebank.dtos.account.AccountCreateDTO;
import com.khalil.ebank.dtos.account.AccountDTO;
import com.khalil.ebank.dtos.client.ClientCreateDTO;
import com.khalil.ebank.dtos.client.ClientDTO;
import com.khalil.ebank.dtos.operation.OperationDTO;
import com.khalil.ebank.dtos.user.UserCreateDTO;
import com.khalil.ebank.dtos.user.UserDTO;
import com.khalil.ebank.entities.Account;
import com.khalil.ebank.entities.Client;
import com.khalil.ebank.entities.Operation;
import com.khalil.ebank.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    // User Mappings
    @Mapping(target = "role", source = "role.name")
    UserDTO fromUser(User user);

    @Mapping(target = "role", ignore = true) // Handled manually or ignored as Role is an Entity
    User toUser(UserDTO userDTO);

    @Mapping(target = "role", ignore = true) // Handled manually or ignored
    @Mapping(target = "passwordHash", ignore = true) // Password handled by service
    User toUser(UserCreateDTO userCreateDTO);

    // Client Mappings
    @Mapping(target = "role", source = "role.name")
    ClientDTO fromClient(Client client);

    @Mapping(target = "role", ignore = true)
    Client toClient(ClientDTO clientDTO);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    Client toClient(ClientCreateDTO clientCreateDTO);

    // Account Mappings
    @Mapping(target = "clientName", expression = "java(account.getClient().getNom() + ' ' + account.getClient().getPrenom())")
    AccountDTO fromAccount(Account account);

    Account toAccount(AccountDTO accountDTO);

    Account toAccount(AccountCreateDTO accountCreateDTO);

    // Operation Mappings
    @Mapping(target = "accountId", source = "account.id")
    OperationDTO fromOperation(Operation operation);
}
