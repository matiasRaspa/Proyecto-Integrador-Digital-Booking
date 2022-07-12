package com.proyectointegrador.proyecto_Integrador_CTD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class BadBookingException extends RuntimeException{
    public BadBookingException(String message) {
        super(message);
    }

    public BadBookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadBookingException(String message, Integer id) {
        super(message + id);
    }
}
