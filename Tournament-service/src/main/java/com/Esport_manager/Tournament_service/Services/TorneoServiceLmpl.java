package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Exceptions.TorneoNoEncontradoException;
import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;
import com.Esport_manager.Tournament_service.Repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Torneo save(TorneoDTO dto) {
        Torneo t = new Torneo();
        t.setNombre(dto.getNombre());
        t.setJuegoId(dto.getJuegoId());
        t.setFechaInicio(dto.getFechaInicio());
        t.setFechaFin(dto.getFechaFin());
        t.setCupoMaximo(dto.getCupoMaximo());
        t.setEstado(dto.getEstado()); // El modelo usa String
        t.setModalidad(dto.getModalidad());
        return repository.save(t);
    }

    @Override
    public Torneo updateById(TorneoDTO dto, Long id) {
        Optional<Torneo> optional = repository.findById(id);
        if (optional.isPresent()) {
            Torneo t = optional.get();
            t.setNombre(dto.getNombre());
            t.setJuegoId(dto.getJuegoId());
            t.setFechaInicio(dto.getFechaInicio());
            t.setFechaFin(dto.getFechaFin());
            t.setCupoMaximo(dto.getCupoMaximo());
            t.setEstado(dto.getEstado());
            t.setModalidad(dto.getModalidad());
            return repository.save(t);
        }
        throw new TorneoNoEncontradoException("El torneo con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Torneo> findByJuegoId(Long juegoId) {
        return repository.findByJuegoId(juegoId);
    }

    @Override
    public List<Torneo> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public List<Torneo> findByFechaInicio(LocalDate fechaInicio) {
        return repository.findByFechaInicio(fechaInicio);
    }
}
