package com.migestion.service;


import com.migestion.model.Admin;

public interface AdminAuthenticationService {
    String authenticate(Admin admin) throws Exception;
}
