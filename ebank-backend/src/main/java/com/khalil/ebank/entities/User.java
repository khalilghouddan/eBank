package com.khalil.ebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users") // 'user' is often a reserved keyword
// table users pcq user tout court ca bug avec mysql parfois
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String passwordHash;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime lastPasswordChange;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false)
    private Role role;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        // date auto, pratique
    }
}
