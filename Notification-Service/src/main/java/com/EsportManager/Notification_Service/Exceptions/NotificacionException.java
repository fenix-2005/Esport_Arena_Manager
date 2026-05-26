package com.EsportManager.Notification_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotificacionException extends RuntimeException {
    public NotificacionException(String mensaje) { super(mensaje); }
}

