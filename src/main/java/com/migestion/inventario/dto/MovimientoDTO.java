package com.migestion.inventario.dto;

import com.migestion.dto.AdminDTO;
import com.migestion.inventario.model.TipoMovimiento;

import java.time.LocalDateTime;

public class MovimientoDTO {
    private Long id;
    private LocalDateTime fecha;
    private TipoMovimiento tipo;
    private Integer cantidad;
    private AdminDTO usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public AdminDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(AdminDTO usuario) {
        this.usuario = usuario;
    }
}
