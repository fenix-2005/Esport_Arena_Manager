package com.EsportManager.Ranking_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RankingNoEncontradoException extends RuntimeException {
    public RankingNoEncontradoException(String mensaje) { super(mensaje); }
}
