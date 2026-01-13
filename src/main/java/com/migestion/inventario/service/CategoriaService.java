package com.migestion.inventario.service;

import com.migestion.inventario.model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria crear(Categoria categoria);
    List<Categoria> listar();
}
