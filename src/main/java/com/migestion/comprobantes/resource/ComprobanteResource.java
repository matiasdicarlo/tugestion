package com.migestion.comprobantes.resource;

import com.migestion.comprobantes.dto.ComprobanteRequestDTO;
import com.migestion.comprobantes.dto.ComprobanteResponseDTO;
import com.migestion.comprobantes.service.ComprobanteService;
import com.migestion.service.AdminAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteResource {

        private final ComprobanteService service;

        private AdminAuthorizationService adminAuthorizationService;

        @Autowired
        public ComprobanteResource(ComprobanteService service, AdminAuthorizationService adminAuthorizationService) {
            this.service = service;
            this.adminAuthorizationService = adminAuthorizationService;
        }

        @PostMapping
        public ResponseEntity<ComprobanteResponseDTO> crear(@RequestBody ComprobanteRequestDTO req, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return ResponseEntity.ok(service.crear(req));
        }

        @GetMapping
        public ResponseEntity<List<ComprobanteResponseDTO>> listar(@RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return ResponseEntity.ok(service.listar());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ComprobanteResponseDTO> obtener(@PathVariable Long id, @RequestHeader("Authorization") String token) {
            adminAuthorizationService.authorize(token);
            return ResponseEntity.ok(service.obtener(id));
        }
    }


