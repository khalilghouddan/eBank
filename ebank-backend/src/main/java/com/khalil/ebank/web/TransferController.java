package com.khalil.ebank.web;

import com.khalil.ebank.dtos.transfer.TransferRequestDTO;
import com.khalil.ebank.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Void> performTransfer(@RequestBody @Valid TransferRequestDTO request) {
        transferService.transfer(
                request.getSourceAccountRib(),
                request.getDestinationAccountRib(),
                request.getAmount(),
                request.getDescription());
        // lance le virement et priez pour que ca march mdr
        return ResponseEntity.ok().build();
    }
}
