package com.Esport_manager.Tournament_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FechaTorneoInvalidaException extends RuntimeException {
    public FechaTorneoInvalidaException(String mensaje) { super(mensaje); }
}
