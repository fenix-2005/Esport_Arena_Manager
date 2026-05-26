package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Exceptions.SancionNoEncontradaException;
import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Repositories.SancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SancionServiceLmpl implements SancionService {

    @Autowired
    private SancionRepository repository;

    @Override
    public List<Sancion> findAll() {
        return repository.findAll();
    }

    @Override
    public Sancion findById(Long id) {
        Optional<Sancion> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new SancionNoEncontradaException("La sancion con id " + id + " no existe");
    }

    @Override
    public Sancion save(Sancion sancion) {
        return repository.save(sancion);
    }

    @Override
    public Sancion updateById(Sancion sancion, Long id) {
        Optional<Sancion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Sancion s = optional.get();
            s.setUsuarioId(sancion.getUsuarioId());
            s.setEquipoId(sancion.getEquipoId());
            s.setMotivo(sancion.getMotivo());
            s.setFechaInicio(sancion.getFechaInicio());
            s.setFechaFin(sancion.getFechaFin());
            s.setEstado(sancion.getEstado());
            s.setSeverida(sancion.getSeverida());
            return repository.save(s);
        }
        throw new SancionNoEncontradaException("La sancion con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
