package com.migestion.inventario.repository;

import com.migestion.inventario.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySku(String sku);

    Optional<Item> findByCodigoBarras(String codigoBarras);

    // Stock por debajo del mínimo
    List<Item> findByStockActualLessThan(Integer stockMinimo);

    // Stock por encima del máximo
    List<Item> findByStockActualGreaterThan(Integer stockMaximo);

    @Query("""
        SELECT i FROM Item i
        WHERE i.activo = true""") List<Item> findActivos();

    @Query("""
        SELECT i FROM Item i
        WHERE i.activo = true AND i.proveedor.id = :proveedorId""")
    List<Item> findActivosByProveedor(Long proveedorId);

    @Query("""
        SELECT i FROM Item i
        WHERE i.activo = true AND i.categoria.id = :categoriaId """) List<Item> findActivosByCategoria(Long categoriaId);

    @Query("""
        SELECT i FROM Item i
        WHERE i.activo = true AND i.proveedor.id = :proveedorId AND i.categoria.id = :categoriaId""")
    List<Item> findActivosByProveedorAndCategoria(Long proveedorId, Long categoriaId);

    // Stock igual o por debajo del mínimo
    @Query("SELECT i FROM Item i WHERE i.stockActual <= i.stockMinimo")
    List<Item> findItemsConStockMinimo();

    // Stock igual o por arriba del máximo
    @Query("SELECT i FROM Item i WHERE i.stockActual >= i.stockMaximo")
    List<Item> findItemsConStockMaximo();

    // Todos los que están en alerta
    @Query("SELECT i FROM Item i WHERE i.stockActual <= i.stockMinimo OR i.stockActual >= i.stockMaximo")
    List<Item> findItemsConAlertas();

    @Query("SELECT MAX(CAST(SUBSTRING(i.codigoBarras, 4, 9) AS long)) FROM Item i WHERE i.codigoBarras LIKE '299%'")
    Long findMaxCodigoBarrasInterno();

    @Query("SELECT COUNT(p) FROM Item p WHERE p.stockActual <= p.stockMinimo")
    long countByStockActualLessThanEqualMinimo();

    @Query("SELECT COUNT(p) FROM Item p WHERE p.stockActual >= p.stockMaximo")
    long countByStockActualGreaterThanEqualMaximo();

    @Query("SELECT SUM(p.stockActual * p.precioCompra) FROM Item p")
    double calcularValorTotalStock();

}
