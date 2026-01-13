package com.migestion.service;


import com.migestion.exception.UsuarioNoEncontradoExeption;
import com.migestion.model.User;
import com.migestion.repository.UserRepository;
import com.migestion.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserAuthorizationService userAuthorizationService;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, UserAuthorizationService userAuthorizationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAuthorizationService = userAuthorizationService;
    }

    @Override
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsuarioNoEncontradoExeption("Username ya en uso");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UsuarioNoEncontradoExeption("Email ya en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User findByUsername(String username)  {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsuarioNoEncontradoExeption("Usuario no encontrado"));
    }





}