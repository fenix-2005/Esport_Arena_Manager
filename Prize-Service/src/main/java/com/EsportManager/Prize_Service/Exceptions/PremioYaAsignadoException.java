package com.EsportManager.Prize_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PremioYaAsignadoException extends RuntimeException {
    public PremioYaAsignadoException(String mensaje) { super(mensaje); }
}
