package com.EsportManager.Match_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PartidaCanceladaException extends RuntimeException {
    public PartidaCanceladaException(String mensaje) { super(mensaje); }
}

