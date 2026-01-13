package com.migestion.service;


import com.migestion.exception.SinPermisoEception;
import com.migestion.model.User;
import com.migestion.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @Autowired
    public UserAuthorizationServiceImpl(JwtTokenUtil jwtTokenUtil, @Lazy UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public User authorize(String token) {
        if (!jwtTokenUtil.verify(token)) {
            throw new SinPermisoEception("Token inválido");
        }

        String username = jwtTokenUtil.getSubject(token);
        return userService.findByUsername(username);
    }
}