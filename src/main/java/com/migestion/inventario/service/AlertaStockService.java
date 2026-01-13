package com.migestion.inventario.service;

import com.migestion.inventario.dto.ItemAlertaDTO;

import java.util.List;

public interface AlertaStockService {
    public List<ItemAlertaDTO> obtenerProductosConStockMinimo();

    public List<ItemAlertaDTO> obtenerProductosConStockMaximo();

    public List<ItemAlertaDTO> obtenerProductosEnAlerta();

}
