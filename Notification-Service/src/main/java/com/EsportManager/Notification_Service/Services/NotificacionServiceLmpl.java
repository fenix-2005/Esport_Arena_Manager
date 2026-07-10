package com.EsportManager.Notification_Service.Services;

import com.EsportManager.Notification_Service.Exceptions.NotificacionNoEncontradaException;
import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Repositories.NotificacionRepository;
import jakarta.validation.constraints.NotNull;
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
    public Notificacion save(@NotNull NotificacionDTO dto) {
        Notificacion n = new Notificacion();
        n.setUsuarioId(dto.getUserId());
        n.setEquipoId(dto.getEquipoId());
        n.setTipo(dto.getTipo());
        n.setMensaje(dto.getMensaje());
        n.setLeida(dto.getLeida() != null ? dto.getLeida() : false);
        n.setFecha(dto.getFecha());
        return repository.save(n);
    }



    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Notificacion> findByEquipoId(Long equipoId) {
        return repository.findByEquipoId(equipoId);
    }

    @Override
    public Notificacion marcarComoLeida(Long id) {
        Notificacion n = findById(id);
        n.setLeida(true);
        return repository.save(n);
    }
}
