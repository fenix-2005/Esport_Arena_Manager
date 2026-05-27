package com.EsportManager.Result_Service.Services;

import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Repositories.ResultadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadosServiceLmpl implements ResultadosService {

    @Autowired
    private ResultadosRepository repository;

    @Override
    public List<Resultados> findAll() {
        return repository.findAll();
    }

    @Override
    public Resultados findById(Long id) {
        Optional<Resultados> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Resultados save(ResultadosDTO dto) {
        Resultados r = new Resultados();
        r.setPartidaId(dto.getPartidaId());
        r.setTorneoId(dto.getTorneoId());
        r.setGanadorId(dto.getGanadorId());
        r.setPuntajeA(dto.getPuntajeA());
        r.setPuntajeB(dto.getPuntajeB());
        r.setCupoMaximo(dto.getCupoMaximo());
        r.setEstadoValidacion(dto.getEstadoValidacion());
        r.setFechaRegistro(dto.getFechaRegistro());
        return repository.save(r);
    }

    @Override
    public Resultados updateById(ResultadosDTO dto, Long id) {
        Optional<Resultados> optional = repository.findById(id);
        if (optional.isPresent()) {
            Resultados r = optional.get();
            r.setPartidaId(dto.getPartidaId());
            r.setTorneoId(dto.getTorneoId());
            r.setGanadorId(dto.getGanadorId());
            r.setPuntajeA(dto.getPuntajeA());
            r.setPuntajeB(dto.getPuntajeB());
            r.setCupoMaximo(dto.getCupoMaximo());
            r.setEstadoValidacion(dto.getEstadoValidacion());
            r.setFechaRegistro(dto.getFechaRegistro());
            return repository.save(r);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Resultados> findByTorneoId(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    @Override
    public List<Resultados> findByPartidaId(Long partidaId) {
        return repository.findByPartidaId(partidaId);
    }
}
