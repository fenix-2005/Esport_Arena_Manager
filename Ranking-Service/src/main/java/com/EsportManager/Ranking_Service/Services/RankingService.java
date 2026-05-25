package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RankingService {

    // Automatiza la tabla de posiciones recalculando la posición exacta de cada jugador.

    @Autowired
    private RankingRepository repository;

    public void recalcularRanking(Long torneoId) {
        List<Ranking> rankings = repository.findByTorneoIdOrderByPuntosDesc(torneoId);
        int posicionActual = 1;

        for (Ranking r : rankings) {
            r.setPosicion(posicionActual);
            posicionActual++;
            repository.save(r);
        }
    }
}
