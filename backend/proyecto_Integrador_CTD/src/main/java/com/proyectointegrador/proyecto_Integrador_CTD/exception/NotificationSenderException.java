package com.proyectointegrador.proyecto_Integrador_CTD.exception;

public class NotificationSenderException extends RuntimeException {

    public NotificationSenderException(String message) {
        super(message);
    }

    public NotificationSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationSenderException(String message, Integer id) {
        super(message + id);
    }
}
