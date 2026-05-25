package com.Esport_manager.Tournament_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneoNoEncontradoException extends RuntimeException {
    public TorneoNoEncontradoException(String mensaje) { super(mensaje); }
}
