package com.migestion.comprobantes.resource;

import com.migestion.comprobantes.dto.ClienteRequestDTO;
import com.migestion.comprobantes.dto.ClienteResponseDTO;
import com.migestion.comprobantes.service.ClienteService;
import com.migestion.service.AdminAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

    private final ClienteService service;

    private AdminAuthorizationService adminAuthorizationService;

    @Autowired
    public ClienteResource(ClienteService service, AdminAuthorizationService adminAuthorizationService) {
        this.service = service;
        this.adminAuthorizationService = adminAuthorizationService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteRequestDTO dto, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody ClienteRequestDTO dto, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> get(@PathVariable Long id, @RequestHeader("Authorization") String token ) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/buscar-por-nombre/{nombre}")
    public ResponseEntity<ClienteResponseDTO> getByNombre(@PathVariable String nombre, @RequestHeader("Authorization") String token ) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.getByNombre(nombre));
    }


    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> list(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        service.logicalDelete(id);
        return ResponseEntity.ok("Cliente dado de baja correctamente.");
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Integer> cantidadClientes(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.cantidadClientes());
    }
}