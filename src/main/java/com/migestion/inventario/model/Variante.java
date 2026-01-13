package com.migestion.inventario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "varientes")
public class Variante {
    @Id
    @GeneratedValue
    private Long id;

    private String atributo;
    private String valor;

    @ManyToOne
    private Item item;
}
