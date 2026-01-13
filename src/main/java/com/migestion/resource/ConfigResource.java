package com.migestion.resource;


import com.migestion.comprobantes.dto.ClienteResponseDTO;
import com.migestion.dto.ConfiguracionDTO;
import com.migestion.model.Configuración;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigResource {

    private final AdminAuthorizationService adminAuthorizationService;
    private final ConfigService service;

    @Autowired
    public ConfigResource(AdminAuthorizationService adminAuthorizationService, ConfigService service) {
        this.adminAuthorizationService = adminAuthorizationService;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ConfiguracionDTO> create(@RequestBody ConfiguracionDTO dto, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.saveOrUpdate(dto));
    }

    @GetMapping
    public ResponseEntity<ConfiguracionDTO> list(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.getConfiguracion());
    }

    @PutMapping
    public ResponseEntity<ConfiguracionDTO> update(@RequestBody ConfiguracionDTO dto, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.ok(service.saveOrUpdate(dto));
    }

}
