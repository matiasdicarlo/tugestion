package com.migestion.inventario.service;

import com.migestion.exception.InventarioException;
import com.migestion.inventario.dto.KpiStockDTO;
import com.migestion.inventario.dto.ProductoMovimientoDTO;
import com.migestion.inventario.dto.StockEvolucionDTO;
import com.migestion.inventario.model.Item;
import com.migestion.inventario.model.MovimientoStock;
import com.migestion.inventario.model.TipoMovimiento;
import com.migestion.inventario.repository.ItemRepository;
import com.migestion.inventario.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteStockServiceImp implements ReporteStockService {

    private MovimientoRepository movimientoRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ReporteStockServiceImp(MovimientoRepository movimientoRepository, ItemRepository itemRepository) {
        this.movimientoRepository = movimientoRepository;
        this.itemRepository = itemRepository;
    }

    public List<StockEvolucionDTO> obtenerEvolucionStock(String codigoBarras) {

            Item item = itemRepository.findByCodigoBarras(codigoBarras)
                    .orElseThrow(() -> new InventarioException("Producto no encontrado"));

            List<MovimientoStock> movimientos =
                    movimientoRepository.findByItemIdOrderByFechaAsc(item.getId());

            List<StockEvolucionDTO> historial = new ArrayList<>();
            int stock = 0;
            stock = item.getStockActual();
            for (MovimientoStock m : movimientos) {
                if (m.getTipo().equals(TipoMovimiento.ENTRADA)) {
                    stock += m.getCantidad();
                } else if (m.getTipo().equals(TipoMovimiento.SALIDA)) {
                    stock -= m.getCantidad();
                }
                historial.add(new StockEvolucionDTO(
                        m.getFecha().toLocalDate(),
                        stock
                ));
            }
            return historial;
        }

    public List<ProductoMovimientoDTO> obtenerRankingProductos(LocalDate desde, LocalDate hasta) {
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(23, 59, 59);
        return movimientoRepository.rankingProductosMasMovimientos(inicio, fin);
    }

    public KpiStockDTO obtenerKpisStock() {
        LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime ahora = LocalDateTime.now();
        long productosMinimo = itemRepository.countByStockActualLessThanEqualMinimo();
        long productosMaximo = itemRepository.countByStockActualGreaterThanEqualMaximo();
        long movimientosMes = movimientoRepository.countByFechaBetween(inicioMes, ahora);
        double valorTotalStock = itemRepository.calcularValorTotalStock();
        return new KpiStockDTO(productosMinimo, productosMaximo, movimientosMes, valorTotalStock, ahora);
    }

    }
