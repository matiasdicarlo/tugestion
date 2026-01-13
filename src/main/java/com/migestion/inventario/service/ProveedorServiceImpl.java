package com.migestion.inventario.service;

import com.migestion.exception.InventarioException;
import com.migestion.inventario.repository.ProveedorRepository;
import com.migestion.inventario.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Proveedor crear(Proveedor proveedor) {
        if (proveedor.getActivo() == null) {
            proveedor.setActivo(true);
        }

        return proveedorRepository.save(proveedor);
    }

    @Override
    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }
    @Override
    public Proveedor actualizar(Long id, Proveedor proveedor) {
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Proveedor no encontrado"));
        existente.setNombre(proveedor.getNombre());
        existente.setTelefono(proveedor.getTelefono());
        existente.setEmail(proveedor.getEmail());
        existente.setDireccion(proveedor.getDireccion());
        existente.setActivo(proveedor.getActivo());

        return proveedorRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Proveedor no encontrado"));

        existente.setActivo(false);
        proveedorRepository.save(existente);
    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Optional<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombre(nombre);
    }
    @Override
    public Integer cantidadProveedores(){
        List<Proveedor> proveedores= proveedorRepository.findAll();
        return proveedores.size();
    }

}
