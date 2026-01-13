package com.migestion.inventario.service;

import com.migestion.inventario.dto.KpiStockDTO;
import com.migestion.inventario.dto.ProductoMovimientoDTO;
import com.migestion.inventario.dto.StockEvolucionDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteStockService {

    public List<StockEvolucionDTO> obtenerEvolucionStock(String codigoBarras);

    public List<ProductoMovimientoDTO> obtenerRankingProductos(LocalDate desde, LocalDate hasta);

    public KpiStockDTO obtenerKpisStock();

}
