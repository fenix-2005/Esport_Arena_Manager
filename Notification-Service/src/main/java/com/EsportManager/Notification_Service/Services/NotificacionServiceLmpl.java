package com.EsportManager.Notification_Service.Services;

import com.EsportManager.Notification_Service.Exceptions.NotificacionNoEncontradaException;
import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceLmpl implements NotificacionService {

    @Autowired
    private NotificacionRepository repository;

    @Override
    public List<Notificacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Notificacion findById(Long id) {
        Optional<Notificacion> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotificacionNoEncontradaException("La notificacion con id " + id + " no existe");
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    @Override
    public Notificacion updateById(Notificacion notificacion, Long id) {
        Optional<Notificacion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Notificacion n = optional.get();
            n.setUsuarioId(notificacion.getUsuarioId());
            n.setEquipoId(notificacion.getEquipoId());
            n.setTipo(notificacion.getTipo());
            n.setMensaje(notificacion.getMensaje());
            n.setLeida(notificacion.getLeida());
            n.setFecha(notificacion.getFecha());
            return repository.save(n);
        }
        throw new NotificacionNoEncontradaException("La notificacion con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
