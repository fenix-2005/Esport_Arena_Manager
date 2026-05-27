package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import java.util.List;
import java.util.Optional;

public interface RankingService {
    List<Ranking> findAll();
    Ranking findById(Long id);
    Ranking save(RankingDTO rankingDTO);
    Ranking updateById(RankingDTO rankingDTO, Long id);
    void deleteById(Long id);
    List<Ranking> findByTorneoId(Long torneoId);
    Optional<Ranking> findByTorneoIdAndParticipanteId(Long torneoId, Long participanteId);
}
