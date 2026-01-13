package com.migestion.inventario.dto;


import java.time.LocalDateTime;

public class KpiStockDTO {
    private long productosEnMinimo;
    private long productosEnMaximo;
    private long totalMovimientosMes;
    private double valorTotalStock;
    private LocalDateTime ultimaActualizacion;


    public KpiStockDTO(long productosEnMinimo, long productosEnMaximo, long totalMovimientosMes, double valorTotalStock, LocalDateTime ultimaActualizacion) {
        this.productosEnMinimo = productosEnMinimo;
        this.productosEnMaximo = productosEnMaximo;
        this.totalMovimientosMes = totalMovimientosMes;
        this.valorTotalStock = valorTotalStock;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public long getProductosEnMinimo() {
        return productosEnMinimo;
    }

    public void setProductosEnMinimo(long productosEnMinimo) {
        this.productosEnMinimo = productosEnMinimo;
    }

    public long getProductosEnMaximo() {
        return productosEnMaximo;
    }

    public void setProductosEnMaximo(long productosEnMaximo) {
        this.productosEnMaximo = productosEnMaximo;
    }

    public long getTotalMovimientosMes() {
        return totalMovimientosMes;
    }

    public void setTotalMovimientosMes(long totalMovimientosMes) {
        this.totalMovimientosMes = totalMovimientosMes;
    }

    public double getValorTotalStock() {
        return valorTotalStock;
    }

    public void setValorTotalStock(double valorTotalStock) {
        this.valorTotalStock = valorTotalStock;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
