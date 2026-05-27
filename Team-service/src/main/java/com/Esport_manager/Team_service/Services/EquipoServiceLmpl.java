package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import com.Esport_manager.Team_service.Repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceLmpl implements EquipoService {

    @Autowired
    private EquipoRepository repository;

    @Override
    public List<Equipo> findAll() {
        return repository.findAll();
    }

    @Override
    public Equipo findById(Long id) {
        Optional<Equipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Equipo save(EquipoDTO dto) {
        Equipo e = new Equipo();
        e.setNombre(dto.getNombre());
        e.setCapitanId(dto.getCapitanId());
        e.setJuegoPrincipalId(dto.getJuegoPrincipalId());
        e.setEstado(dto.getEstado());
        return repository.save(e);
    }

    @Override
    public Equipo updateById(EquipoDTO dto, Long id) {
        Optional<Equipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            Equipo e = optional.get();
            e.setNombre(dto.getNombre());
            e.setCapitanId(dto.getCapitanId());
            e.setJuegoPrincipalId(dto.getJuegoPrincipalId());
            e.setEstado(dto.getEstado());
            return repository.save(e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Equipo> findByJuegoPrincipalId(Long juegoPrincipalId) {
        return repository.findByJuegoPrincipalId(juegoPrincipalId);
    }

    @Override
    public List<Equipo> findByCapitanId(Long capitanId) {
        return repository.findByCapitanId(capitanId);
    }

    @Override
    public List<Equipo> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }
}
