package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    // pagination, pr pas tout charger d'un coup
    Page<Operation> findByAccountId(Long accountId, Pageable pageable);
}
