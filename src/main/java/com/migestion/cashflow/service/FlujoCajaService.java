package com.migestion.cashflow.service;

import com.migestion.cashflow.dto.MovimientoCajaResponseDTO;
import com.migestion.cashflow.dto.RegistroCajaResponseDTO;
import com.migestion.cashflow.model.FlujoCajaMovimiento;
import com.migestion.cashflow.model.RegistroCaja;

import java.time.LocalDateTime;
import java.util.List;

public interface FlujoCajaService {
    public MovimientoCajaResponseDTO register(FlujoCajaMovimiento tx);
    public List<FlujoCajaMovimiento> getBetween(LocalDateTime start, LocalDateTime end);
    public Double getBalance(LocalDateTime start, LocalDateTime end);
    public RegistroCajaResponseDTO openRegistroCaja(double initialAmount);
    public RegistroCajaResponseDTO closeCashRegister(Double finalAmount);
}
