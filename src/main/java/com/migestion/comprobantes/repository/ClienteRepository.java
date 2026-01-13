package com.migestion.comprobantes.repository;

import com.migestion.comprobantes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findById(long id);

    Cliente findByNombreFantasia(String nombre);

    List<Cliente> findByActivoTrue();

    boolean existsByCuit(String cuit);
}

