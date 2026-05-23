package Services;

import Exceptions.NotificacionException;
import Exceptions.NotificacionNoEncontradaException;
import Models.Notificacion;
import Repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    // Envía alertas requiriendo un destinatario válido y permite marcarlas como leídas.

    @Autowired
    private NotificacionRepository repository;

    public Notificacion crearNotificacion(Notificacion notificacion) {
        if (notificacion.getUserId() == null && notificacion.getEquipoId() == null) {
            throw new NotificacionException("Debe especificar un usuario o equipo destinatario.");
        }
        notificacion.setLeida(false);
        return repository.save(notificacion);
    }

    public Notificacion marcarComoLeida(Long id) {
        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new NotificacionNoEncontradaException("Notificación no encontrada."));

        notificacion.setLeida(true);
        return repository.save(notificacion);
    }
}
