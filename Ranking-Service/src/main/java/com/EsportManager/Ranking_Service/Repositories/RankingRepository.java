package com.EsportManager.Ranking_Service.Repositories;

import com.EsportManager.Ranking_Service.Models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {


    List<Ranking> findByTorneoId(Long torneoId);


    Optional<Ranking> findByTorneoIdAndParticipanteId(Long torneoId, Long participanteId);

}
