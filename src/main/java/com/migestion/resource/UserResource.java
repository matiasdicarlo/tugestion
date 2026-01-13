package com.migestion.resource;

import com.migestion.dto.UserLoginDTO;
import com.migestion.dto.UserRegistrationDTO;
import com.migestion.exception.SinPermisoEception;
import com.migestion.model.User;
import com.migestion.security.JwtTokenUtil;
import com.migestion.service.UserAuthorizationService;
import com.migestion.service.UserService;
import com.migestion.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserResource {
    private UserService userService;
    private JwtTokenUtil jwtTokenUtil;
    private PasswordEncoder passwordEncoder;
    private UserAuthorizationService userAuthorizationService;

    @Autowired
    public UserResource(UserService userService, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, UserAuthorizationService userAuthorizationService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userAuthorizationService= userAuthorizationService;
    }

    // POST /users/register - Registrar un nuevo usuario.
    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationDTO dto)  {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        userService.register(user);
        return "Usuario registrado correctamente";
    }

    // POST /users/login - Autenticar a un usuario y retornar un JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto) {
        User user = userService.findByUsername(dto.getUsername());
        String token = jwtTokenUtil.generateToken(user.getUsername());
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        if (!passwordEncoder.verify(dto.getPassword(), user.getPassword())) {
            throw new SinPermisoEception("Credenciales inválidas");
        }
        return ResponseEntity.ok(responseBody);
    }



}