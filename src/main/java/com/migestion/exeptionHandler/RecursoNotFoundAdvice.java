package com.migestion.exeptionHandler;


import com.migestion.exception.ComprobantesException;
import com.migestion.exception.InventarioException;
import com.migestion.exception.SinPermisoEception;
import com.migestion.exception.UsuarioNoEncontradoExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecursoNotFoundAdvice {


    @ExceptionHandler (SinPermisoEception.class)
    @ResponseStatus
    public ResponseEntity<String> SinPermisoFoundHandler(SinPermisoEception exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler (UsuarioNoEncontradoExeption.class)
    @ResponseStatus
    public ResponseEntity<String> UsuarioNoEncontradoExeption(UsuarioNoEncontradoExeption exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler (InventarioException.class)
    @ResponseStatus
    public ResponseEntity<String> IventarioException(InventarioException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler (ComprobantesException.class)
    @ResponseStatus
    public ResponseEntity<String> ComprobanteExeption(ComprobantesException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
