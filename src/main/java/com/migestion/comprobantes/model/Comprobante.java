package com.migestion.comprobantes.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoComprobante tipo;

    @ManyToOne
    private Cliente cliente;

    private LocalDateTime fecha;

    private String numero;

    private Double total;

    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL)
    private List<ComprobanteItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoComprobante getTipo() {
        return tipo;
    }

    public void setTipo(TipoComprobante tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ComprobanteItem> getItems() {
        return items;
    }

    public void setItems(List<ComprobanteItem> items) {
        this.items = items;
    }
}
