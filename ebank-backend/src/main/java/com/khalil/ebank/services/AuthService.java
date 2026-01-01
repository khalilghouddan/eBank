package com.khalil.ebank.services;

import com.khalil.ebank.dtos.auth.AuthResponseDTO;
import com.khalil.ebank.dtos.auth.LoginRequestDTO;
import com.khalil.ebank.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.khalil.ebank.entities.User;
import com.khalil.ebank.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        // genère le token et c parti mon kiki

        return AuthResponseDTO.builder()
                .accessToken(jwt)
                .build();
    }

    public void changePassword(String username, String currentPassword, String newPassword) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, currentPassword));

        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        // mdp a jour, oublie pas le hash sinon c nimp
    }
}
