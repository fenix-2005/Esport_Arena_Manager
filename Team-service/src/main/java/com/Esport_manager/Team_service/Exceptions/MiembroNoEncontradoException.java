package com.Esport_manager.Team_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MiembroNoEncontradoException extends RuntimeException {
    public MiembroNoEncontradoException(String mensaje) { super(mensaje); }
}
