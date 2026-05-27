package com.EsportManager.Notification_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NotificacionDTO {
    private Long id;
    private Long userId;
    private Long equipoId;
    private String tipo;
    private String mensaje;
    private Boolean leida;
    private LocalDate fecha;
}
