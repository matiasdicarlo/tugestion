package com.migestion.service;


import com.migestion.model.Admin;
import com.migestion.security.JwtTokenUtil;
import com.migestion.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationServiceImp implements AdminAuthenticationService {


    private AdminService adminService;


    private PasswordEncoder passwordEncoder;


    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AdminAuthenticationServiceImp(AdminService adminService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String authenticate(Admin admin) throws Exception {
        Admin dbAdmin = adminService.findByEmail(admin.getEmail());

        if (!passwordEncoder.verify(admin.getPassword(), dbAdmin.getPassword())) {
            throw new Exception("Invalid password");
        }

        return jwtTokenUtil.generateToken(admin.getEmail());
    }
}
