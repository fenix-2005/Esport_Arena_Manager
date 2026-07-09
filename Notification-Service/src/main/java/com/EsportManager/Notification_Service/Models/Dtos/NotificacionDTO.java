package com.EsportManager.Notification_Service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class NotificacionDTO {
    private Long id;

    @NotNull(message = "El ID de usuario no puede ser nulo")
    private Long userId;

    private Long equipoId;

    @NotBlank(message = "El tipo de notificación no puede estar vacío")
    private String tipo;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;

    @NotNull(message = "El estado de lectura no puede ser nulo")
    private Boolean leida;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;
}
