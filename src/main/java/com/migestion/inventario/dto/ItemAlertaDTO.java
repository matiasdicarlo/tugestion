package com.migestion.inventario.dto;

public record ItemAlertaDTO(
        Long id,
        String nombre,
        int stockActual,
        int stockMinimo,
        int stockMaximo
) {}
