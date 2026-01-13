package com.migestion.inventario.repository;

import com.migestion.inventario.dto.ProductoMovimientoDTO;
import com.migestion.inventario.model.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoStock, Long> {

    List<MovimientoStock> findByItemId(Long id);
    void deleteById(Long id);
    List<MovimientoStock> findByItemIdOrderByFechaAsc(Long itemId);

    @Query("SELECT new com.migestion.inventario.dto.ProductoMovimientoDTO( m.item.id, m.item.nombre, COUNT(m)) FROM MovimientoStock m WHERE m.tipo = 'SALIDA' AND m.fecha BETWEEN :desde AND :hasta GROUP BY m.item.id, m.item.nombre ORDER BY COUNT(m) DESC")
    List<ProductoMovimientoDTO> rankingProductosMasMovimientos(
            LocalDateTime desde,
            LocalDateTime hasta
    );

    long countByFechaBetween(LocalDateTime desde, LocalDateTime hasta);


}