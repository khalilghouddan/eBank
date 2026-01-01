package com.khalil.ebank.services;

import com.khalil.ebank.dtos.client.ClientCreateDTO;
import com.khalil.ebank.entities.Client;
import com.khalil.ebank.entities.Role;
import com.khalil.ebank.enums.RoleName;
import com.khalil.ebank.exceptions.ResourceNotFoundException;

import com.khalil.ebank.repositories.ClientRepository;
import com.khalil.ebank.repositories.RoleRepository;
import com.khalil.ebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public Client createClient(ClientCreateDTO dto) {
        // RG_5
        if (userRepository.existsByEmail(dto.getEmail())) {
            // check si mail existe deja, sinon ca bug aprè
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Login already exists");
        }
        // RG_4
        if (clientRepository.existsByIdentiteNum(dto.getIdentiteNum())) {
            throw new IllegalArgumentException("Identity Number already exists");
        }

        // RG_6: Default Password for Dev
        // mdp par defaut c 123, pr tester vite fait
        String rawPassword = "123";

        Role clientRole = roleRepository.findByName(RoleName.CLIENT)
                .orElseThrow(() -> new ResourceNotFoundException("Role CLIENT not found"));

        Client client = new Client();
        client.setLogin(dto.getLogin());
        client.setEmail(dto.getEmail());
        client.setPasswordHash(passwordEncoder.encode(rawPassword));
        client.setEnabled(true);
        client.setCreatedAt(LocalDateTime.now());
        client.setRole(clientRole);

        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setIdentiteNum(dto.getIdentiteNum());
        client.setDateNaissance(dto.getDateNaissance());
        client.setAdressePostale(dto.getAdressePostale());

        Client savedClient = clientRepository.save(client);
        // c bon on a save le client en base

        // RG_7
        String emailBody = String.format(
                "Welcome %s,\n\nYour account has been created.\nLogin: %s\nPassword: %s\n\nPlease change your password upon login.",
                savedClient.getNom(), savedClient.getLogin(), rawPassword);
        emailService.sendEmail(savedClient.getEmail(), "Welcome to eBank", emailBody);

        return savedClient;
    }

    public Client getCurrentClient(String login) {
        return clientRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }
}
