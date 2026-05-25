package com.EsportManager.Prize_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PremioNoEncontradoException extends RuntimeException {
    public PremioNoEncontradoException(String mensaje) { super(mensaje); }
}
