package com.Esport_manager.Auth_service.Services;

import com.Esport_manager.Auth_service.Exceptions.CuentaNoEncontradaException;
import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaAccesoServiceLmpl implements CuentaAccesoService {

    @Autowired
    private CuentaAccesoRepository repository;

    @Override
    public List<CuentaAcceso> findAll() {
        return repository.findAll();
    }

    @Override
    public CuentaAcceso findById(Long id) {
        Optional<CuentaAcceso> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CuentaNoEncontradaException("La cuenta con id " + id + " no existe");
    }

    @Override
    public CuentaAcceso save(CuentaAcceso cuentaAcceso) {
        return repository.save(cuentaAcceso);
    }

    @Override
    public CuentaAcceso updateById(CuentaAcceso cuentaAcceso, Long id) {
        Optional<CuentaAcceso> optional = repository.findById(id);
        if (optional.isPresent()) {
            CuentaAcceso c = optional.get();
            c.setEmail(cuentaAcceso.getEmail());
            c.setPassword(cuentaAcceso.getPassword());
            c.setRol(cuentaAcceso.getRol());
            c.setEstado(cuentaAcceso.getEstado());
            c.setFechaCreacion(cuentaAcceso.getFechaCreacion());
            return repository.save(c);
        }
        throw new CuentaNoEncontradaException("La cuenta con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
