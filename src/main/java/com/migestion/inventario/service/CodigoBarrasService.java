package com.migestion.inventario.service;

public interface CodigoBarrasService {

    public String generarCodigoInterno();

    public int calcularChecksumEAN13(String codeWithoutChecksum);
}
