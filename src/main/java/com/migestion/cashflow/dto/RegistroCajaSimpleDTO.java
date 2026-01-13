package com.migestion.cashflow.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroCajaSimpleDTO {
    private Long id;
    private LocalDateTime openedAt;
    private Double initialAmount;
}