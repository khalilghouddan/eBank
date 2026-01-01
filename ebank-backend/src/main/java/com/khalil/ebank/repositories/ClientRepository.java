package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // on cherche par num d'identité, gère l'unicité un peu
    boolean existsByIdentiteNum(String identiteNum);

    Optional<Client> findByLogin(String login);
}
