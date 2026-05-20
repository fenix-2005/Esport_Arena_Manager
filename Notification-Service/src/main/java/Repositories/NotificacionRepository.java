package Repositories;

import Models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository <Notificacion, Long>{
    // "Listar notificaciones por usuario"
    List<Notificacion> findByUsuarioId(Long usuarioId);

    // "Listar notificaciones por equipo"
    List<Notificacion> findByEquipoId(Long equipoId);
}
