package com.EsportManager.Match_Service.Services;

import com.EsportManager.Match_Service.Exceptions.PartidaNoEncontradaException;
import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Repositories.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaServiceLmpl implements PartidaService {

    @Autowired
    private PartidaRepository repository;

    @Override
    public List<Partida> findAll() {
        return repository.findAll();
    }

    @Override
    public Partida findById(Long id) {
        Optional<Partida> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new PartidaNoEncontradaException("La partida con id " + id + " no existe");
    }

    @Override
    public Partida save(Partida partida) {
        return repository.save(partida);
    }

    @Override
    public Partida updateById(Partida partida, Long id) {
        Optional<Partida> optional = repository.findById(id);
        if (optional.isPresent()) {
            Partida p = optional.get();
            p.setTorneoId(partida.getTorneoId());
            p.setParticipanteAId(partida.getParticipanteAId());
            p.setParticipanteBId(partida.getParticipanteBId());
            p.setRonda(partida.getRonda());
            p.setFechaHora(partida.getFechaHora());
            p.setEstado(partida.getEstado());
            return repository.save(p);
        }
        throw new PartidaNoEncontradaException("La partida con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
