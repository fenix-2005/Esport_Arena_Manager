package com.Esport_manager.Game_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JuegoDuplicadoException extends RuntimeException {
    public JuegoDuplicadoException(String mensaje) { super(mensaje); }
}

