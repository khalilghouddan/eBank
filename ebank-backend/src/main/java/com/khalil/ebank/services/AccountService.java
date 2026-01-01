package com.khalil.ebank.services;

import com.khalil.ebank.entities.Account;
import com.khalil.ebank.entities.Client;
import com.khalil.ebank.exceptions.ResourceNotFoundException;
import com.khalil.ebank.repositories.AccountRepository;
import com.khalil.ebank.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public Account getAccountByRib(String rib) {
        return accountRepository.findByRib(rib)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with RIB: " + rib));
    }

    @Transactional
    public Account createAccount(com.khalil.ebank.dtos.account.AccountCreateDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Account account = Account.builder()
                .rib(generateUniqueRib())
                .client(client)
                .solde(dto.getInitialBalance() != null ? dto.getInitialBalance() : BigDecimal.ZERO)
                .status(com.khalil.ebank.enums.AccountStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }

    private String generateUniqueRib() {
        // Simple generation logic (uuid hash or random digits)
        // In real app, must follow bank standards.
        // generation a l'arrache, a changer plus tard pr un vrai truc
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
    }
}
