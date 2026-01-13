package com.migestion.inventario.dto;

import java.time.LocalDate;

public class StockEvolucionDTO {
    private LocalDate fecha;
    private int stock;

    public StockEvolucionDTO(LocalDate fecha, int stock) {
        this.fecha = fecha;
        this.stock = stock;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getStock() {
        return stock;
    }
}
