package com.EsportManager.Match_Service.Services;

import com.EsportManager.Match_Service.Exceptions.PartidaNoEncontradaException;
import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
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
    public Partida save(PartidaDTO dto) {
        Partida p = new Partida();
        p.setTorneoId(dto.getTorneoId());
        p.setParticipanteAId(dto.getParticipanteAId());
        p.setParticipanteBId(dto.getParticipanteBId());
        p.setRonda(dto.getRonda());
        p.setFechaHora(dto.getFechaHora());
        p.setEstado(dto.getEstado());
        return repository.save(p);
    }

    @Override
    public Partida updateById(PartidaDTO dto, Long id) {
        Optional<Partida> optional = repository.findById(id);
        if (optional.isPresent()) {
            Partida p = optional.get();
            p.setTorneoId(dto.getTorneoId());
            p.setParticipanteAId(dto.getParticipanteAId());
            p.setParticipanteBId(dto.getParticipanteBId());
            p.setRonda(dto.getRonda());
            p.setFechaHora(dto.getFechaHora());
            p.setEstado(dto.getEstado());
            return repository.save(p);
        }
        throw new PartidaNoEncontradaException("La partida con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Partida> findByTorneoId(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    @Override
    public List<Partida> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public List<Partida> findByRonda(Integer ronda) {
        return repository.findByRonda(ronda);
    }
}
