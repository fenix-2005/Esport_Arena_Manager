package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Models.Ranking;
import java.util.List;

public interface RankingService {
    List<Ranking> findAll();
    Ranking findById(Long id);
    Ranking save(Ranking ranking);
    Ranking updateById(Ranking ranking, Long id);
    void deleteById(Long id);
}
