package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Exceptions.PremioNoEncontradoException;
import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Repositories.PremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PremioServiceLmpl implements PremioService {

    @Autowired
    private PremioRepository repository;

    @Override
    public List<Premio> findAll() {
        return repository.findAll();
    }

    @Override
    public Premio findById(Long id) {
        Optional<Premio> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new PremioNoEncontradoException("El premio con id " + id + " no existe");
    }

    @Override
    public Premio save(Premio premio) {
        return repository.save(premio);
    }

    @Override
    public Premio updateById(Premio premio, Long id) {
        Optional<Premio> optional = repository.findById(id);
        if (optional.isPresent()) {
            Premio p = optional.get();
            p.setTorneoId(premio.getTorneoId());
            p.setPosicion(premio.getPosicion());
            p.setDescripcion(premio.getDescripcion());
            p.setValor(premio.getValor());
            p.setEstado(premio.getEstado());
            return repository.save(p);
        }
        throw new PremioNoEncontradoException("El premio con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
