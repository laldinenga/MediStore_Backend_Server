package com.store.medicalstore.repository;

import com.store.medicalstore.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByEmail (String email);
}
