package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Exceptions.RankingNoEncontradoException;
import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import com.EsportManager.Ranking_Service.Repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceLmpl implements RankingService {

    @Autowired
    private RankingRepository repository;

    @Override
    public List<Ranking> findAll() {
        return repository.findAll();
    }

    @Override
    public Ranking findById(Long id) {
        Optional<Ranking> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RankingNoEncontradoException("El ranking con id " + id + " no existe");
    }

    @Override
    public Ranking save(RankingDTO dto) {
        Ranking r = new Ranking();
        r.setTorneoId(dto.getTorneoId());
        r.setParticipanteId(dto.getParticipanteId());
        r.setPuntos(dto.getPuntos());
        r.setVictorias(dto.getVictorias());
        r.setDerrotas(dto.getDerrotas());
        r.setDiferencia(dto.getDiferencia());
        r.setPosicion(dto.getPosicion());
        return repository.save(r);
    }

    @Override
    public Ranking updateById(RankingDTO dto, Long id) {
        Optional<Ranking> optional = repository.findById(id);
        if (optional.isPresent()) {
            Ranking r = optional.get();
            r.setTorneoId(dto.getTorneoId());
            r.setParticipanteId(dto.getParticipanteId());
            r.setPuntos(dto.getPuntos());
            r.setVictorias(dto.getVictorias());
            r.setDerrotas(dto.getDerrotas());
            r.setDiferencia(dto.getDiferencia());
            r.setPosicion(dto.getPosicion());
            return repository.save(r);
        }
        throw new RankingNoEncontradoException("El ranking con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Ranking> findByTorneoId(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    @Override
    public Optional<Ranking> findByTorneoIdAndParticipanteId(Long torneoId, Long participanteId) {
        return repository.findByTorneoIdAndParticipanteId(torneoId, participanteId);
    }
}
