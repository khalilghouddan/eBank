package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    // check mail existant
    boolean existsByEmail(String email);
}
