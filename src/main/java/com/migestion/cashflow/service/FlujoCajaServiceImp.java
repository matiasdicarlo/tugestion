package com.migestion.cashflow.service;

import com.migestion.cashflow.dto.MovimientoCajaResponseDTO;
import com.migestion.cashflow.dto.RegistroCajaResponseDTO;
import com.migestion.cashflow.model.FlujoCajaMovimiento;
import com.migestion.cashflow.model.RegistroCaja;
import com.migestion.cashflow.model.TipoTransaccion;
import com.migestion.cashflow.repository.FlujoCajaRegisterRepository;
import com.migestion.cashflow.repository.FlujoCajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlujoCajaServiceImp implements FlujoCajaService{

        private FlujoCajaRepository repository;
        private FlujoCajaRegisterRepository flujoCajaRegisterRepository ;

        @Autowired
        public FlujoCajaServiceImp(FlujoCajaRepository repository, FlujoCajaRegisterRepository flujoCajaRegisterRepository) {
            this.repository = repository;
            this.flujoCajaRegisterRepository = flujoCajaRegisterRepository;
        }


        @Override
        public MovimientoCajaResponseDTO register(FlujoCajaMovimiento tx) {
            RegistroCaja cash = flujoCajaRegisterRepository.findByOpenTrue()
                    .orElseThrow(() -> new RuntimeException("No hay una caja abierta."));
            tx.setRegistroCaja(cash);
            FlujoCajaMovimiento saved = repository.save(tx);
            MovimientoCajaResponseDTO dto = new MovimientoCajaResponseDTO();
            dto.setId(saved.getId());
            dto.setAmount(saved.getAmount());
            dto.setDescription(saved.getDescription());
            dto.setType(saved.getType());
            dto.setCreatedAt(saved.getCreatedAt());
            dto.setRegistroCajaId(cash.getId());
            return dto;
        }
        @Override
        public List<FlujoCajaMovimiento> getBetween(LocalDateTime start, LocalDateTime end) {
            return repository.findBycreatedAtBetween(start, end);
        }
        @Override
        public Double getBalance(LocalDateTime start, LocalDateTime end) {
            List<FlujoCajaMovimiento> txs = repository.findBycreatedAtBetween(start, end);
            return txs.stream()
                    .mapToDouble(t ->
                            t.getType() == TipoTransaccion.INGRESO
                                    ? t.getAmount()
                                    : -t.getAmount()
                    )
                    .sum();
        }
        @Override
        public RegistroCajaResponseDTO openRegistroCaja(double initialAmount) {
            // Si ya existe una caja abierta, error
            if (flujoCajaRegisterRepository.findByOpenTrue().isPresent()) {
                throw new RuntimeException("Ya existe una caja abierta.");
            }
            RegistroCaja cash = new RegistroCaja();
            cash.setInitialAmount(initialAmount);
            cash.setOpenedAt(LocalDateTime.now());
            cash.setOpen(true);
            RegistroCaja saved = flujoCajaRegisterRepository.save(cash);
            return toDTO(saved);
        }
        @Override
        public RegistroCajaResponseDTO closeCashRegister(Double finalAmount) {
            RegistroCaja cash = flujoCajaRegisterRepository.findByOpenTrue()
                .orElseThrow(() -> new RuntimeException("No hay una caja abierta."));
            cash.setFinalAmount(finalAmount);
            cash.setClosedAt(LocalDateTime.now());
            cash.setOpen(false);
            RegistroCaja saved = flujoCajaRegisterRepository.save(cash);
            return toDTO(saved);
        }


    private RegistroCajaResponseDTO toDTO(RegistroCaja cash) {
        RegistroCajaResponseDTO dto = new RegistroCajaResponseDTO();
        dto.setId(cash.getId());
        dto.setOpenedAt(cash.getOpenedAt());
        dto.setClosedAt(cash.getClosedAt());
        dto.setInitialAmount(cash.getInitialAmount());
        dto.setFinalAmount(cash.getFinalAmount());
        dto.setOpen(cash.isOpen());
        return dto;
    }


}
