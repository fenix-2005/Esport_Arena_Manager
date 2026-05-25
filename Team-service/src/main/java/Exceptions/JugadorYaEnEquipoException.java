package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JugadorYaEnEquipoException extends RuntimeException {
    public JugadorYaEnEquipoException(String mensaje) { super(mensaje); }
}

