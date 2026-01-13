package com.migestion.cashflow.resource;

import com.migestion.cashflow.dto.MovimientoCajaResponseDTO;
import com.migestion.cashflow.dto.RegistroCajaResponseDTO;
import com.migestion.cashflow.model.FlujoCajaMovimiento;
import com.migestion.cashflow.model.RegistroCaja;
import com.migestion.cashflow.service.FlujoCajaService;
import com.migestion.service.AdminAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flujo-caja")
public class FlujoCajaResource {
        private FlujoCajaService service;
        private AdminAuthorizationService adminAuthorizationService;

        @Autowired
        public FlujoCajaResource(FlujoCajaService service, AdminAuthorizationService adminAuthorizationService) {
            this.service = service;
            this.adminAuthorizationService = adminAuthorizationService;
        }

        @PostMapping("/register")
        public ResponseEntity<MovimientoCajaResponseDTO> register(@RequestBody FlujoCajaMovimiento tx, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return ResponseEntity.ok(service.register(tx));
        }

        @GetMapping("/balance")
        public ResponseEntity<Double> getBalance(@RequestParam String start, @RequestParam String end, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime startDateTime = LocalDateTime.parse(start.trim(), fmt);
            LocalDateTime endDateTime = LocalDateTime.parse(end.trim(), fmt);
            return ResponseEntity.ok(service.getBalance(startDateTime , endDateTime)
            );
        }

        @GetMapping("/balance/dia")
        public Double balanceDelDia(@RequestParam LocalDate date) {
            return service.getBalance(date.atStartOfDay(), date.atTime(23, 59, 59));
        }

        @GetMapping("/list")
        public ResponseEntity<List<MovimientoCajaResponseDTO>> list(@RequestParam String start, @RequestParam String end, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime startDateTime = LocalDateTime.parse(start.trim(), fmt);
            LocalDateTime endDateTime = LocalDateTime.parse(end.trim(), fmt);
            return ResponseEntity.ok(service.getBetween(startDateTime, endDateTime)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList()));

        }

        @PostMapping("/abrir")
        public RegistroCajaResponseDTO abrir(@RequestBody RegistroCaja req, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return service.openRegistroCaja(req.getInitialAmount());
        }

        @PostMapping("/cerrar")
        public RegistroCajaResponseDTO cerrar(@RequestBody RegistroCaja req, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return service.closeCashRegister(req.getFinalAmount());
        }


    private MovimientoCajaResponseDTO toDTO(FlujoCajaMovimiento m) {
        MovimientoCajaResponseDTO dto = new MovimientoCajaResponseDTO();
        dto.setId(m.getId());
        dto.setType(m.getType());
        dto.setDescription(m.getDescription());
        dto.setAmount(m.getAmount());
        dto.setCreatedAt(m.getCreatedAt());
        dto.setRegistroCajaId(m.getRegistroCaja().getId());
        return dto;
    }

}
