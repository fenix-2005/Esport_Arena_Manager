package com.EsportManager.Result_Service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultadoNoEncontradoException extends RuntimeException {
    public ResultadoNoEncontradoException(String mensaje) { super(mensaje); }
}
