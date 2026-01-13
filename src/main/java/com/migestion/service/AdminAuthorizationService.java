package com.migestion.service;


import com.migestion.model.Admin;

public interface AdminAuthorizationService {
    Admin authorize(String token);
}
