
package com.khalil.ebank.web;

import com.khalil.ebank.dtos.account.AccountCreateDTO;
import com.khalil.ebank.dtos.account.AccountDTO;
import com.khalil.ebank.dtos.operation.OperationDTO;
import com.khalil.ebank.mappers.BankAccountMapper;
import com.khalil.ebank.services.AccountService;
import com.khalil.ebank.repositories.OperationRepository;
import com.khalil.ebank.entities.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    private final AccountService accountService;
    private final BankAccountMapper mapper;
    private final OperationRepository operationRepository; // Could be wrapped in Service

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountCreateDTO createDTO) {
        return ResponseEntity.ok(mapper.fromAccount(accountService.createAccount(createDTO)));
    }

    @GetMapping("/{id}/operations")
    public ResponseEntity<Page<OperationDTO>> getAccountOperations(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // TODO: Verify user owns account if role is CLIENT
        // todo: verif sécu ici c pa encore top, nimp qui peut voir les ops ??

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Operation> operations = operationRepository.findByAccountId(id, pageable);

        return ResponseEntity.ok(operations.map(mapper::fromOperation));
    }
}
