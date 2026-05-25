package com.EsportManager.Result_Service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResultadoYaValidadoException extends RuntimeException {
    public ResultadoYaValidadoException(String mensaje) { super(mensaje); }
}
