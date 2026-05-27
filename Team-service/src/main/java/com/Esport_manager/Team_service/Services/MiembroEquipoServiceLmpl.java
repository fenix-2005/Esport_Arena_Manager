package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Exceptions.MiembroNoEncontradoException;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
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
    public MiembroEquipo save(MiembroEquipoDTO dto) {
        MiembroEquipo m = new MiembroEquipo();
        m.setEquipoId(dto.getEquipoId());
        m.setUsuarioId(dto.getUsuarioId());
        m.setRolEquipo(dto.getRolEquipo());
        return repository.save(m);
    }

    @Override
    public MiembroEquipo updateById(MiembroEquipoDTO dto, Long id) {
        Optional<MiembroEquipo> optional = repository.findById(id);
        if (optional.isPresent()) {
            MiembroEquipo m = optional.get();
            m.setEquipoId(dto.getEquipoId());
            m.setUsuarioId(dto.getUsuarioId());
            m.setRolEquipo(dto.getRolEquipo());
            return repository.save(m);
        }
        throw new MiembroNoEncontradoException("El miembro con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MiembroEquipo> findByEquipoId(Long equipoId) {
        return List.of();
    }

    @Override
    public void eliminarPorEquipoYUsuario(Long equipoId, Long usuarioId) {

    }
}
