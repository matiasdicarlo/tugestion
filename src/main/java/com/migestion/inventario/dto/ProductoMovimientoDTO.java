package com.migestion.inventario.dto;

public class ProductoMovimientoDTO {
    private Long id;
    private String nombre;
    private Long movimientos;

    public ProductoMovimientoDTO(Long id, String nombre, Long movimientos) {
        this.id = id;
        this.nombre = nombre;
        this.movimientos = movimientos;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getMovimientos() { return movimientos; }
}
