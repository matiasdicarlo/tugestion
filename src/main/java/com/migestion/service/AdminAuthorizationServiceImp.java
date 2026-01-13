package com.migestion.service;

import com.migestion.exception.SinPermisoEception;
import com.migestion.model.Admin;
import com.migestion.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthorizationServiceImp implements AdminAuthorizationService {


    private JwtTokenUtil jwtTokenUtil;


    private AdminService adminService;

    @Autowired
    public AdminAuthorizationServiceImp(JwtTokenUtil jwtTokenUtil, AdminService adminService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.adminService = adminService;
    }

    @Override
    public Admin authorize(String token) {
        // Verificar si el token es válido
        if (!jwtTokenUtil.verify(token)) {
            throw new SinPermisoEception("Token inválido o expirado");
        }


        String email = jwtTokenUtil.getSubject(token);

        // Buscar el Admin
        Admin admin = adminService.findByEmail(email);
        if (admin == null) {
            throw new SinPermisoEception("Admin no encontrado para el token proporcionado");
        }

        return admin;
    }
}
