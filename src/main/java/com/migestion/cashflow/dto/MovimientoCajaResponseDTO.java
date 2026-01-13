package com.migestion.cashflow.dto;


import com.migestion.cashflow.model.TipoTransaccion;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoCajaResponseDTO {
    private Long id;
    private TipoTransaccion type;
    private String description;
    private Double amount;
    private LocalDateTime createdAt;
    private Long registroCajaId;
}