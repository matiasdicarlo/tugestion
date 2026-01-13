package com.migestion.inventario.service;

import com.migestion.inventario.repository.MovimientoRepository;
import com.migestion.inventario.model.MovimientoStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImp implements MovimientoService{

    private final MovimientoRepository movimientoRepository;

    @Autowired
    public MovimientoServiceImp(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }


    @Override
    public List<MovimientoStock> listarMovimientos() {
        return movimientoRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoStock> buscarPorItemId(Long id) {
        return movimientoRepository.findByItemId(id);
    }

    @Override
    public void eliminar(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public void eliminarTodos() {
        movimientoRepository.deleteAll();
    }


}
