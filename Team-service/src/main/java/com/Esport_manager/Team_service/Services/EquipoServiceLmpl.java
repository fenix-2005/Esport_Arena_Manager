package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.Equipo;
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
    public Equipo save(Equipo equipo) {
        return repository.save(equipo);
    }

    @Override
    public Equipo updateById(Equipo equipo, Long id) {
        Optional<Equipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            Equipo e = optional.get();
            e.setNombre(equipo.getNombre());
            e.setCapitanId(equipo.getCapitanId());
            e.setJuegoPrincipalId(equipo.getJuegoPrincipalId());
            e.setEstado(equipo.getEstado());
            return repository.save(e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
