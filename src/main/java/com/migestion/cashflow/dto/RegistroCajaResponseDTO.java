package com.migestion.cashflow.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroCajaResponseDTO {
    private Long id;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private Double initialAmount;
    private Double finalAmount;
    private Boolean open;
}