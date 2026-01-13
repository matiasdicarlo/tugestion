package com.migestion.service;


import com.migestion.model.User;

public interface UserAuthorizationService {
    User authorize(String token) ;
}