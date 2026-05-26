package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Exceptions.RankingNoEncontradoException;
import com.EsportManager.Ranking_Service.Models.Ranking;
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
    public Ranking save(Ranking ranking) {
        return repository.save(ranking);
    }

    @Override
    public Ranking updateById(Ranking ranking, Long id) {
        Optional<Ranking> optional = repository.findById(id);
        if (optional.isPresent()) {
            Ranking r = optional.get();
            r.setTorneoId(ranking.getTorneoId());
            r.setParticipanteId(ranking.getParticipanteId());
            r.setPuntos(ranking.getPuntos());
            r.setVictorias(ranking.getVictorias());
            r.setDerrotas(ranking.getDerrotas());
            r.setDiferencia(ranking.getDiferencia());
            r.setPosicion(ranking.getPosicion());
            return repository.save(r);
        }
        throw new RankingNoEncontradoException("El ranking con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
