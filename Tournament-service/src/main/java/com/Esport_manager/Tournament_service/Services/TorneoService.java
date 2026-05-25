package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Exceptions.FechaTorneoInvalidaException;
import com.Esport_manager.Tournament_service.Exceptions.TorneoEnCursoException;
import com.Esport_manager.Tournament_service.Exceptions.TorneoNoEncontradoException;
import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class TorneoService {

    // Valida fechas de inscripción en torneos y bloquea cambios de reglas si ya están en curso.

    @Autowired
    private TorneoRepository repository;

    public Torneo crearTorneo(Torneo torneo, LocalDate fechaCierreInscripcion) {
        if (torneo.getFechaInicio().before(torneo.getFechaFin())) {
            throw new FechaTorneoInvalidaException("El torneo no puede iniciar antes del cierre de inscripciones.");
        }
        torneo.setEstado("BORRADOR");
        return repository.save(torneo);
    }

    public Torneo actualizarReglas(Long id, String nuevaModalidad) {
        Torneo torneo = repository.findById(id)
                .orElseThrow(() -> new TorneoNoEncontradoException("Torneo no encontrado en la base de datos."));

        if ("EN CURSO".equals(torneo.getEstado())) {
            throw new TorneoEnCursoException("Reglas bloqueadas: El torneo ya está en disputa.");
        }
        torneo.setModalidad(nuevaModalidad);
        return repository.save(torneo);
    }
}
