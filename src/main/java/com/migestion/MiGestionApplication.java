package com.migestion;

import com.migestion.security.JwtTokenUtil;
import com.migestion.security.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.migestion")
public class MiGestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiGestionApplication.class, args);

		//Para generar la primera contraseña para Admin
		//String contraseña= new GenerarContraseña().generarContraseña();
		//System.out.printf(contraseña);

	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PasswordEncoder();
	}
	@Bean
	public JwtTokenUtil jwtTokenUtil(){
		return new JwtTokenUtil();
	}
	}


