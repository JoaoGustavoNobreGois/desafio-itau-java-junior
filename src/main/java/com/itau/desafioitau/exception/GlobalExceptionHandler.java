package com.itau.desafioitau.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handlerIllegalArgument(IllegalArgumentException ex){
        if(ex.getMessage().equals("Valor negativo") || ex.getMessage().equals("Futuro")){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneric(Exception ex){
        return ResponseEntity.badRequest().build();
    }
}
