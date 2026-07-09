package com.Esport_manager.Auth_service.Services;

import com.Esport_manager.Auth_service.Exceptions.CuentaNoEncontradaException;
import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaAccesoServiceLmpl implements CuentaAccesoService {

    @Autowired
    private CuentaAccesoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<CuentaAcceso> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaAcceso findById(Long id) {
        Optional<CuentaAcceso> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CuentaNoEncontradaException("La cuenta con id " + id + " no existe");
    }

    @Override
    @Transactional
    public CuentaAcceso save(CuentaAccesoDTO dto) {
        CuentaAcceso c = new CuentaAcceso();
        c.setEmail(dto.getEmail());
        c.setPassword(dto.getPassword());
        c.setRol(dto.getRol());
        c.setEstado(dto.getEstado());
        c.setFechaCreacion(dto.getFechaCreacion());
        return repository.save(c);
    }

    @Override
    @Transactional
    public CuentaAcceso updateById(CuentaAccesoDTO dto, Long id) {
        Optional<CuentaAcceso> optional = repository.findById(id);
        if (optional.isPresent()) {
            CuentaAcceso c = optional.get();
            c.setEmail(dto.getEmail());
            c.setPassword(dto.getPassword());
            c.setRol(dto.getRol());
            c.setEstado(dto.getEstado());
            c.setFechaCreacion(dto.getFechaCreacion());
            return repository.save(c);
        }
        throw new CuentaNoEncontradaException("La cuenta con id " + id + " no existe");
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaAcceso> findByRol(String rol) {
        return repository.findByRol(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaAcceso> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaAcceso findByEmail(String email) {
        Optional<CuentaAcceso> optional = repository.findByEmail(email);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CuentaNoEncontradaException("La cuenta con email " + email + " no existe");
    }

}
