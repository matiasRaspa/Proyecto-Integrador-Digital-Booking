package com.proyectointegrador.proyecto_Integrador_CTD.exception;

import java.time.DateTimeException;
import java.util.Date;

public class ExceptionResponse {
    private Date dateException;
    private String errorMessage;
    private String detailMessage;


    public ExceptionResponse(Date dateException, String errorMessage, String detailMessage) {
        super();
        this.dateException = dateException;
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }

    public Date getDateTimeException() {
        return dateException;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
