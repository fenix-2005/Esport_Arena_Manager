package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceLmpl implements InscripcionService {

    @Autowired
    private InscripcionRepository repository;

    @Override
    public List<Inscripcion> findAll() {
        return repository.findAll();
    }

    @Override
    public Inscripcion findById(Long id) {
        Optional<Inscripcion> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        return repository.save(inscripcion);
    }

    @Override
    public Inscripcion updateById(Inscripcion inscripcion, Long id) {
        Optional<Inscripcion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Inscripcion i = optional.get();
            i.setTorneoId(inscripcion.getTorneoId());
            i.setEquipoId(inscripcion.getEquipoId());
            i.setJugadorId(inscripcion.getJugadorId());
            i.setTipoParticipante(inscripcion.getTipoParticipante());
            i.setEstado(inscripcion.getEstado());
            i.setFechaInscripcion(inscripcion.getFechaInscripcion());
            return repository.save(i);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
