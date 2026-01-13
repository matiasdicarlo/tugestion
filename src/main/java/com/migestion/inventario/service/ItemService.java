package com.migestion.inventario.service;

import com.migestion.inventario.model.Item;
import com.migestion.model.Admin;
import com.migestion.inventario.model.MovimientoStock;
import com.migestion.inventario.model.TipoMovimiento;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item crear(Item item);
    Item actualizar(Long id, Item item);
    void eliminar(Long id); // eliminación lógica
    List<Item> listarTodos();
    List<Item> listarActivos(Long proveedor, Long categoria);
    Optional<Item> buscarPorId(Long id);
    Optional<Item> buscarPorSku(String sku);
    public MovimientoStock registrarMovimientoEntrada(Long productoId, TipoMovimiento tipo, int cantidad, Admin admin);

    public Integer cantidadProductos();
    public MovimientoStock registrarMovimientoSalida(Long productoId, TipoMovimiento tipo, int cantidad, Admin admin);
}
