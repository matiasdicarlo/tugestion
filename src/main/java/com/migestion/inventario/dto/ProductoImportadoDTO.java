package com.migestion.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoImportadoDTO {
    private String sku;
    private String nombre;
    private String descripcion;
    private String marca;
    private String modelo;
    private String codigoBarras;
    private String unidadMedida;
    private Double precioUnitario;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private String ubicacion;


}