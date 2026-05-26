package com.EsportManager.Notification_Service.Services;

import com.EsportManager.Notification_Service.Models.Notificacion;
import java.util.List;

public interface NotificacionService {
    List<Notificacion> findAll();
    Notificacion findById(Long id);
    Notificacion save(Notificacion notificacion);
    Notificacion updateById(Notificacion notificacion, Long id);
    void deleteById(Long id);
}
