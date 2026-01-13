package com.migestion.inventario.resource;

import com.migestion.inventario.model.Notificacion;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notificaciones")
public class NotificationResource {

    private AdminAuthorizationService adminAuthorizationService;

    private final NotificationService notificacionService;

    @Autowired
    public NotificationResource(AdminAuthorizationService adminAuthorizationService, NotificationService notificacionService) {
        this.adminAuthorizationService = adminAuthorizationService;
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(notificacionService.listarNoLeidas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        Notificacion n= notificacionService.obtenerNotificacion(id);
        return ResponseEntity.ok(notificacionService.marcarComoLeida(n));
    }
}
