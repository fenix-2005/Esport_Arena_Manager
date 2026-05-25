package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EquipoSinCapitanException extends RuntimeException {
    public EquipoSinCapitanException(String mensaje) { super(mensaje); }
}
