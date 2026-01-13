package com.migestion.service;


import com.migestion.model.Admin;

import java.util.Optional;

public interface AdminService {

        Admin findByEmail(String email);
        Optional<Admin> findById(Long id);

}
