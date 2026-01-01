package com.khalil.ebank.services;

import com.khalil.ebank.entities.Account;
import com.khalil.ebank.entities.Operation;
import com.khalil.ebank.entities.Transfer;
import com.khalil.ebank.enums.AccountStatus;
import com.khalil.ebank.enums.OperationType;
import com.khalil.ebank.enums.TransferStatus;
import com.khalil.ebank.exceptions.AccountClosedException;
import com.khalil.ebank.exceptions.InsufficientBalanceException;
import com.khalil.ebank.repositories.AccountRepository;
import com.khalil.ebank.repositories.OperationRepository;
import com.khalil.ebank.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final TransferRepository transferRepository;

    @Transactional
    public void transfer(String sourceRib, String destRib, BigDecimal amount, String description) {
        // 1. Retrieve accounts
        // recupere les 2 comptes via leur RIB, tt simple
        Account source = accountService.getAccountByRib(sourceRib);
        Account dest = accountService.getAccountByRib(destRib);

        // 2. Validate source status (RG_11)
        if (source.getStatus() != AccountStatus.OPEN) {
            // si le compte est fermé on stop tt de suite !!
            throw new AccountClosedException("Source account is not OPEN");
        }

        // Validate destination status (Optional but good practice)
        if (dest.getStatus() != AccountStatus.OPEN) {
            throw new AccountClosedException("Destination account is not OPEN");
        }

        // 3. Validate balance (RG_12)
        if (source.getSolde().compareTo(amount) < 0) {
            // verif si y'a assez de tune, sinon marche pa
            throw new InsufficientBalanceException("Insufficient balance in source account");
        }

        // 4. Perform Transfer (Debit/Credit) (RG_14)
        source.setSolde(source.getSolde().subtract(amount));
        dest.setSolde(dest.getSolde().add(amount));

        accountRepository.save(source);
        accountRepository.save(dest);
        // on save les 2, esperons que ca pete pas entre les deux mdr

        // 5. Create Transfer Entity
        Transfer transfer = Transfer.builder()
                .sourceAccount(source)
                .destAccount(dest)
                .montant(amount)
                .motif(description)
                .executedAt(LocalDateTime.now())
                .status(TransferStatus.COMPLETED)
                .build();

        transferRepository.save(transfer);

        // 6. Create Operations (Rg_15)
        Operation debitOp = Operation.builder()
                .account(source)
                .type(OperationType.DEBIT)
                .montant(amount)
                .libelle("Transfer to " + destRib + ": " + description)
                .transfer(transfer)
                .createdAt(LocalDateTime.now())
                .build();

        Operation creditOp = Operation.builder()
                .account(dest)
                .type(OperationType.CREDIT)
                .montant(amount)
                .libelle("Transfer from " + sourceRib + ": " + description)
                .transfer(transfer)
                .createdAt(LocalDateTime.now())
                .build();

        operationRepository.save(debitOp);
        operationRepository.save(creditOp);
    }
}
