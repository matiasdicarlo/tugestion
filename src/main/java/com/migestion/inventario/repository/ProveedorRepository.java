package com.migestion.inventario.repository;

import com.migestion.inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByActivoTrue(); // útil si querés listar solo los activos
    Optional<Proveedor> findByNombre(String nombre);
    Optional<Proveedor> findByEmail(String email);
}