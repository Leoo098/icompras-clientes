package com.leodev.icompras.clientes.controller.handler;

import com.leodev.icompras.clientes.service.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErroResponse> handleValidationException(ValidationException e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErroResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    public record ErroResponse(int status, String mensagem){}
}
