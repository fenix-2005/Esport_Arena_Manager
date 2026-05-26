package com.EsportManager.Match_Service.Repositories;

import com.EsportManager.Match_Service.Models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository <Partida, Long>{


    List<Partida> findByTorneoId(String torneoId);

    List<Partida> findByEstado(String estado);

    List<Partida> findByRonda(int ronda);
}
