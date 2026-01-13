package com.migestion.cashflow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flujo_caja_movimiento")
public class FlujoCajaMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion type; // INCOME, EXPENSE

    private String description;

    private Double amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private RegistroCaja registroCaja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TipoTransaccion getType() {
        return type;
    }

    public void setType(TipoTransaccion type) {
        this.type = type;
    }

    public RegistroCaja getRegistroCaja() {
        return registroCaja;
    }

    public void setRegistroCaja(RegistroCaja registroCaja) {
        this.registroCaja = registroCaja;
    }
}
