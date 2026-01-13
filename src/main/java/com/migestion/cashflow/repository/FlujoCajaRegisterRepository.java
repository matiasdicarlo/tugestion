package com.migestion.cashflow.repository;

import com.migestion.cashflow.model.RegistroCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FlujoCajaRegisterRepository extends JpaRepository<RegistroCaja, Long> {

    Optional<RegistroCaja> findByOpenTrue();
}
