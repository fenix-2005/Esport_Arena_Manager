package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Exceptions.MiembroNoEncontradoException;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Repositories.MiembroEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroEquipoServiceLmpl implements MiembroEquipoService {

    @Autowired
    private MiembroEquipoRepository repository;

    @Override
    public List<MiembroEquipo> findAll() {
        return repository.findAll();
    }

    @Override
    public MiembroEquipo findById(Long id) {
        Optional<MiembroEquipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new MiembroNoEncontradoException("El miembro con id " + id + " no existe");
    }

    @Override
    public MiembroEquipo save(MiembroEquipo miembroEquipo) {
        return repository.save(miembroEquipo);
    }

    @Override
    public MiembroEquipo updateById(MiembroEquipo miembroEquipo, Long id) {
        Optional<MiembroEquipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            MiembroEquipo m = optional.get();
            m.setEquipoId(miembroEquipo.getEquipoId());
            m.setUsuarioId(miembroEquipo.getUsuarioId());
            m.setRolEquipo(miembroEquipo.getRolEquipo());
            return repository.save(m);
        }
        throw new MiembroNoEncontradoException("El miembro con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
