package com.khalil.ebank.repositories;

import com.khalil.ebank.entities.Role;
import com.khalil.ebank.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // trouve le role par son nom, easy
    Optional<Role> findByName(RoleName name);
}
