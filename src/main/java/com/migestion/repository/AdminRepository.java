package com.migestion.repository;

import com.migestion.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

    Optional<Admin> findById(Long id);
}