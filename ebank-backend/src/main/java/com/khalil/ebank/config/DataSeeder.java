package com.khalil.ebank.config;

import com.khalil.ebank.entities.Role;
import com.khalil.ebank.entities.User;
import com.khalil.ebank.enums.RoleName;
import com.khalil.ebank.repositories.RoleRepository;
import com.khalil.ebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // 1. Init Roles
            if (roleRepository.count() == 0) {
                Role clientRole = Role.builder().name(RoleName.CLIENT).build();
                Role agentRole = Role.builder().name(RoleName.AGENT_GUICHET).build();
                Role adminRole = Role.builder().name(RoleName.ADMIN).build();
                roleRepository.saveAll(List.of(clientRole, agentRole, adminRole));
            }

            // 2. Init Agent User
            if (!userRepository.findByLogin("khalil44").isPresent()) {
                Role agentRole = roleRepository.findByName(RoleName.AGENT_GUICHET).orElseThrow();

                User agent = User.builder()
                        .login("khalil44")
                        .email("khalil44@ebank.com")
                        .passwordHash(passwordEncoder.encode("khalil44"))
                        .role(agentRole)
                        .createdAt(LocalDateTime.now())
                        .enabled(true)
                        .build();

                userRepository.save(agent);
                System.out.println("CREATED AGENT USER: login='khalil44', password='khalil44'");
            }
        };
    }
}
