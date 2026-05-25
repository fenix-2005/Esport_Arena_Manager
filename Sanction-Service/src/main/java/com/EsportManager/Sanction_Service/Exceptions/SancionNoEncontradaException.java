package com.EsportManager.Sanction_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SancionNoEncontradaException extends RuntimeException {
    public SancionNoEncontradaException(String mensaje) { super(mensaje); }
}
