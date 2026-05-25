package com.EsportManager.Sanction_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FechasSancionInvalidasException extends RuntimeException {
    public FechasSancionInvalidasException(String mensaje) { super(mensaje); }
}

