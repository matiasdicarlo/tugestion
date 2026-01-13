package com.migestion.inventario.service;

import com.migestion.inventario.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    Proveedor crear(Proveedor proveedor);
    List<Proveedor> listar();
    Proveedor actualizar(Long id, Proveedor proveedor);
    void eliminar(Long id); // Eliminación lógica
    Optional<Proveedor> buscarPorId(Long id);

    public Integer cantidadProveedores();
    Optional<Proveedor> buscarPorNombre(String nombre);

}
