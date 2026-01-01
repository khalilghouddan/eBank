package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // trouver par rib, le classique
    Optional<Account> findByRib(String rib);
}
