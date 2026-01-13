package com.migestion.repository;

import com.migestion.model.Configuración;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigRepository extends JpaRepository<Configuración, Long> {

    Optional<Configuración> findById(Long id);
}
