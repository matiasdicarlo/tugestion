package com.migestion.security;

public class GenerarContraseña {

   //Clase para generar contraseña Encriptada para admin por primera vez

    private PasswordEncoder passwordEncoder= new PasswordEncoder();

    public String generarContraseña(){
        return passwordEncoder.encode("admin123");
    }


}
