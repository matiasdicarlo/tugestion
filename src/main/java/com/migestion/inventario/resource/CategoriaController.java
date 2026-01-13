package com.migestion.inventario.resource;

import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.model.Categoria;
import com.migestion.inventario.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private AdminAuthorizationService adminAuthorizationService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService, AdminAuthorizationService adminAuthorizationService) {
        this.categoriaService = categoriaService;
        this.adminAuthorizationService = adminAuthorizationService;
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crear(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(categoriaService.listar());
    }
}
