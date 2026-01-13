package com.migestion.inventario.resource;

import com.migestion.inventario.dto.ItemAlertaDTO;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.service.AlertaStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaStockController {

    private AlertaStockService alertaStockService;

    private AdminAuthorizationService adminAuthorizationService;

    @Autowired
    public AlertaStockController(AlertaStockService alertaStockService, AdminAuthorizationService adminAuthorizationService) {
        this.alertaStockService = alertaStockService;
        this.adminAuthorizationService = adminAuthorizationService;
    }

    // Productos con stock en o por debajo del mínimo
    @GetMapping("/stock-minimo")
    public List<ItemAlertaDTO> getStockMinimo(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return alertaStockService.obtenerProductosConStockMinimo();
    }

    // Productos con stock en o por encima del máximo
    @GetMapping("/stock-maximo")
    public List<ItemAlertaDTO> getStockMaximo(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return alertaStockService.obtenerProductosConStockMaximo();
    }

    // Productos con cualquier alerta
    @GetMapping("/stock-alertas")
    public List<ItemAlertaDTO> getProductosEnAlerta(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return alertaStockService.obtenerProductosEnAlerta();
    }
}
