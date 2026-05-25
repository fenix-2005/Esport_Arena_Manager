package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Exceptions.InscripcionDuplicadaException;
import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {

    // Inscribe participantes y previene que el mismo equipo reserve dos cupos en un torneo.

    @Autowired
    private InscripcionRepository repository;

    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        if (repository.findByEquipoIdAndTorneoId(inscripcion.getTorneoId(), inscripcion.getEquipoId()).isPresent()) {
            throw new InscripcionDuplicadaException("El equipo ya se encuentra inscrito en este torneo.");
        }
        inscripcion.setEstado("PENDIENTE");
        return repository.save(inscripcion);
    }
}
