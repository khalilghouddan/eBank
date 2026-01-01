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
import com.khalil.ebank.entities.Role;
import com.khalil.ebank.entities.User;
import com.khalil.ebank.enums.RoleName;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T15:12:50+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public UserDTO fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRole( userRoleName( user ) );
        userDTO.setCreatedAt( user.getCreatedAt() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setEnabled( user.isEnabled() );
        userDTO.setId( user.getId() );
        userDTO.setLogin( user.getLogin() );

        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.createdAt( userDTO.getCreatedAt() );
        user.email( userDTO.getEmail() );
        user.enabled( userDTO.isEnabled() );
        user.id( userDTO.getId() );
        user.login( userDTO.getLogin() );

        return user.build();
    }

    @Override
    public User toUser(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.email( userCreateDTO.getEmail() );
        user.login( userCreateDTO.getLogin() );

        return user.build();
    }

    @Override
    public ClientDTO fromClient(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setRole( clientRoleName( client ) );
        clientDTO.setCreatedAt( client.getCreatedAt() );
        clientDTO.setEmail( client.getEmail() );
        clientDTO.setEnabled( client.isEnabled() );
        clientDTO.setId( client.getId() );
        clientDTO.setLogin( client.getLogin() );
        clientDTO.setAccounts( accountListToAccountDTOList( client.getAccounts() ) );
        clientDTO.setAdressePostale( client.getAdressePostale() );
        clientDTO.setDateNaissance( client.getDateNaissance() );
        clientDTO.setIdentiteNum( client.getIdentiteNum() );
        clientDTO.setNom( client.getNom() );
        clientDTO.setPrenom( client.getPrenom() );

        return clientDTO;
    }

    @Override
    public Client toClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client.ClientBuilder<?, ?> client = Client.builder();

        client.createdAt( clientDTO.getCreatedAt() );
        client.email( clientDTO.getEmail() );
        client.enabled( clientDTO.isEnabled() );
        client.id( clientDTO.getId() );
        client.login( clientDTO.getLogin() );
        client.accounts( accountDTOListToAccountList( clientDTO.getAccounts() ) );
        client.adressePostale( clientDTO.getAdressePostale() );
        client.dateNaissance( clientDTO.getDateNaissance() );
        client.identiteNum( clientDTO.getIdentiteNum() );
        client.nom( clientDTO.getNom() );
        client.prenom( clientDTO.getPrenom() );

        return client.build();
    }

    @Override
    public Client toClient(ClientCreateDTO clientCreateDTO) {
        if ( clientCreateDTO == null ) {
            return null;
        }

        Client.ClientBuilder<?, ?> client = Client.builder();

        client.email( clientCreateDTO.getEmail() );
        client.login( clientCreateDTO.getLogin() );
        client.adressePostale( clientCreateDTO.getAdressePostale() );
        client.dateNaissance( clientCreateDTO.getDateNaissance() );
        client.identiteNum( clientCreateDTO.getIdentiteNum() );
        client.nom( clientCreateDTO.getNom() );
        client.prenom( clientCreateDTO.getPrenom() );

        return client.build();
    }

    @Override
    public AccountDTO fromAccount(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setCreatedAt( account.getCreatedAt() );
        accountDTO.setId( account.getId() );
        accountDTO.setRib( account.getRib() );
        accountDTO.setSolde( account.getSolde() );
        accountDTO.setStatus( account.getStatus() );

        accountDTO.setClientName( account.getClient().getNom() + ' ' + account.getClient().getPrenom() );

        return accountDTO;
    }

    @Override
    public Account toAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.createdAt( accountDTO.getCreatedAt() );
        account.id( accountDTO.getId() );
        account.rib( accountDTO.getRib() );
        account.solde( accountDTO.getSolde() );
        account.status( accountDTO.getStatus() );

        return account.build();
    }

    @Override
    public Account toAccount(AccountCreateDTO accountCreateDTO) {
        if ( accountCreateDTO == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        return account.build();
    }

    @Override
    public OperationDTO fromOperation(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setAccountId( operationAccountId( operation ) );
        operationDTO.setId( operation.getId() );
        operationDTO.setLibelle( operation.getLibelle() );
        operationDTO.setMontant( operation.getMontant() );
        operationDTO.setType( operation.getType() );

        return operationDTO;
    }

    private RoleName userRoleName(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        RoleName name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private RoleName clientRoleName(Client client) {
        if ( client == null ) {
            return null;
        }
        Role role = client.getRole();
        if ( role == null ) {
            return null;
        }
        RoleName name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<AccountDTO> accountListToAccountDTOList(List<Account> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountDTO> list1 = new ArrayList<AccountDTO>( list.size() );
        for ( Account account : list ) {
            list1.add( fromAccount( account ) );
        }

        return list1;
    }

    protected List<Account> accountDTOListToAccountList(List<AccountDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Account> list1 = new ArrayList<Account>( list.size() );
        for ( AccountDTO accountDTO : list ) {
            list1.add( toAccount( accountDTO ) );
        }

        return list1;
    }

    private Long operationAccountId(Operation operation) {
        if ( operation == null ) {
            return null;
        }
        Account account = operation.getAccount();
        if ( account == null ) {
            return null;
        }
        Long id = account.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
