package com.khalil.ebank.web;

import com.khalil.ebank.dtos.client.ClientCreateDTO;
import com.khalil.ebank.dtos.client.ClientDTO;
import com.khalil.ebank.mappers.BankAccountMapper;
import com.khalil.ebank.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {

    private final ClientService clientService;
    private final BankAccountMapper mapper;

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientCreateDTO createDTO) {
        // creer client vite f, on renvoi le dto mapé
        return ResponseEntity.ok(mapper.fromClient(clientService.createClient(createDTO)));
    }

    @org.springframework.web.bind.annotation.GetMapping("/current")
    public ResponseEntity<ClientDTO> getCurrentClient(java.security.Principal principal) {
        return ResponseEntity.ok(mapper.fromClient(clientService.getCurrentClient(principal.getName())));
    }
}
