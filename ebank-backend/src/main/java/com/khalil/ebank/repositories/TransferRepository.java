package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    // rien de special ici pr l'instant
}
