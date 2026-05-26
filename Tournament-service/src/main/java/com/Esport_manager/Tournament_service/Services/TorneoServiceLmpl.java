package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Exceptions.TorneoNoEncontradoException;
import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoServiceLmpl implements TorneoService {

    @Autowired
    private TorneoRepository repository;

    @Override
    public List<Torneo> findAll() {
        return repository.findAll();
    }

    @Override
    public Torneo findById(Long id) {
        Optional<Torneo> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new TorneoNoEncontradoException("El torneo con id " + id + " no existe");
    }

    @Override
    public Torneo save(Torneo torneo) {
        return repository.save(torneo);
    }

    @Override
    public Torneo updateById(Torneo torneo, Long id) {
        Optional<Torneo> optional = repository.findById(id);
        if (optional.isPresent()) {
            Torneo t = optional.get();
            t.setNombre(torneo.getNombre());
            t.setJuegoId(torneo.getJuegoId());
            t.setFechaInicio(torneo.getFechaInicio());
            t.setFechaFin(torneo.getFechaFin());
            t.setCupoMaximo(torneo.getCupoMaximo());
            t.setEstado(torneo.getEstado());
            t.setModalidad(torneo.getModalidad());
            return repository.save(t);
        }
        throw new TorneoNoEncontradoException("El torneo con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
