package com.EsportManager.Result_Service.Services;

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
    public Resultados save(Resultados resultados) {
        return repository.save(resultados);
    }

    @Override
    public Resultados updateById(Resultados resultados, Long id) {
        Optional<Resultados> optional = repository.findById(id);
        if (optional.isPresent()) {
            Resultados r = optional.get();
            r.setPartidaId(resultados.getPartidaId());
            r.setTorneoId(resultados.getTorneoId());
            r.setGanadorId(resultados.getGanadorId());
            r.setPuntajeA(resultados.getPuntajeA());
            r.setPuntajeB(resultados.getPuntajeB());
            r.setCupoMaximo(resultados.getCupoMaximo());
            r.setEstadoValidacion(resultados.getEstadoValidacion());
            r.setFechaRegistro(resultados.getFechaRegistro());
            return repository.save(r);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
