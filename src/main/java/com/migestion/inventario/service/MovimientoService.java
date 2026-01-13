package com.migestion.inventario.service;


import com.migestion.inventario.model.MovimientoStock;

import java.util.List;

public interface MovimientoService {

    List<MovimientoStock> listarMovimientos();
    List<MovimientoStock> buscarPorItemId(Long id);
    void eliminar(Long id);

    void eliminarTodos();
}
