package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Exceptions.PremioNoEncontradoException;
import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
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
    public Premio save(PremioDTO dto) {
        Premio p = new Premio();
        p.setTorneoId(dto.getTorneoId());
        p.setPosicion(dto.getPosicion());
        p.setDescripcion(dto.getDescripcion());
        p.setValor(dto.getValor());
        p.setEstado(dto.getEstado());
        return repository.save(p);
    }

    @Override
    public Premio updateById(PremioDTO dto, Long id) {
        Optional<Premio> optional = repository.findById(id);
        if (optional.isPresent()) {
            Premio p = optional.get();
            p.setTorneoId(dto.getTorneoId());
            p.setPosicion(dto.getPosicion());
            p.setDescripcion(dto.getDescripcion());
            p.setValor(dto.getValor());
            p.setEstado(dto.getEstado());
            return repository.save(p);
        }
        throw new PremioNoEncontradoException("El premio con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Premio> findByTorneoId(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    @Override
    public List<Premio> findByPosicion(Integer posicion) {
        return repository.findByPosicion(posicion);
    }
}
