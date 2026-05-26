package com.Esport_manager.Auth_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CorreoDuplicadoException extends RuntimeException {
    public CorreoDuplicadoException(String mensaje) { super(mensaje); }
}
