package com.migestion.inventario.resource;

import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.model.Proveedor;
import com.migestion.inventario.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private AdminAuthorizationService adminAuthorizationService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService, AdminAuthorizationService adminAuthorizationService) {
        this.proveedorService = proveedorService;
        this.adminAuthorizationService= adminAuthorizationService;
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(proveedor));
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(proveedorService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(proveedorService.actualizar(id, proveedor));
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable String nombre, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return proveedorService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cantidad")
    public Integer cantidaItems(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return proveedorService.cantidadProveedores();
    }

}
