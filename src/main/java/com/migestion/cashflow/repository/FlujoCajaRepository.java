package com.migestion.cashflow.repository;

import com.migestion.cashflow.model.FlujoCajaMovimiento;
import com.migestion.cashflow.model.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlujoCajaRepository extends JpaRepository<FlujoCajaMovimiento, Long> {

        List<FlujoCajaMovimiento> findBycreatedAtBetween(LocalDateTime start, LocalDateTime end);

        List<FlujoCajaMovimiento> findByType(TipoTransaccion type);
    }
