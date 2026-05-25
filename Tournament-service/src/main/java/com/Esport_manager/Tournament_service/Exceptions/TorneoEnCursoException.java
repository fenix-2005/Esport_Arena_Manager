package com.Esport_manager.Tournament_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TorneoEnCursoException extends RuntimeException {
    public TorneoEnCursoException(String mensaje) { super(mensaje); }
}
