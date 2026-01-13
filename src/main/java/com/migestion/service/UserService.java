package com.migestion.service;



import com.migestion.model.User;

public interface UserService {
    User register(User user) ;
    User findByUsername(String username) ;


}