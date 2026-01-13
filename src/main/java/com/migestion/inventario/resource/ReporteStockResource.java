package com.migestion.inventario.resource;

import com.migestion.inventario.dto.KpiStockDTO;
import com.migestion.inventario.dto.ProductoMovimientoDTO;
import com.migestion.inventario.dto.StockEvolucionDTO;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.service.ReporteStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteStockResource {


    private ReporteStockService reporteStockService;

    private AdminAuthorizationService adminAuthorizationService;

    @Autowired
    public ReporteStockResource(ReporteStockService reporteStockService, AdminAuthorizationService adminAuthorizationService) {
        this.reporteStockService = reporteStockService;
        this.adminAuthorizationService = adminAuthorizationService;
    }

    @GetMapping("/stock-evolucion/{codigoBarras}")
        public ResponseEntity<List<StockEvolucionDTO>> getEvolucionStock(@PathVariable String codigoBarras, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return ResponseEntity.ok(reporteStockService.obtenerEvolucionStock(codigoBarras));
        }

    @GetMapping("/productos-mas-movimientos")
    public ResponseEntity<List<ProductoMovimientoDTO>> ranking(@RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate desde, @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok( reporteStockService.obtenerRankingProductos(desde, hasta));
    }

    @GetMapping("/kpis-stock")
    public KpiStockDTO obtenerKpisStock(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return reporteStockService.obtenerKpisStock();
    }
    }
