package com.EsportManager.Notification_Service.Services;

import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Models.Notificacion;
import java.util.List;

public interface NotificacionService {
    List<Notificacion> findAll();
    Notificacion findById(Long id);
    Notificacion save(NotificacionDTO notificacionDTO);
    void deleteById(Long id);
    List<Notificacion> findByUsuarioId(Long usuarioId);
    List<Notificacion> findByEquipoId(Long equipoId);
    Notificacion marcarComoLeida(Long id);
}
