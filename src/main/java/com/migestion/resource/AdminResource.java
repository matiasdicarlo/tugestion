package com.migestion.resource;


import com.migestion.dto.AdminAuthenticationRequestDTO;
import com.migestion.service.AdminAuthenticationService;
import com.migestion.model.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminResource {

    private final AdminAuthenticationService authenticationService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminResource(AdminAuthenticationService authenticationService, ModelMapper modelMapper) {
        this.authenticationService = authenticationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody AdminAuthenticationRequestDTO request) {
        try {
            Admin admin = modelMapper.map(request, Admin.class);
            String token = authenticationService.authenticate(admin);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}


