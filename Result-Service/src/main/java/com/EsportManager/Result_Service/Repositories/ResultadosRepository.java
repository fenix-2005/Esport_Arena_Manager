package com.EsportManager.Result_Service.Repositories;

import com.EsportManager.Result_Service.Models.Resultados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadosRepository extends JpaRepository<Resultados, Long> {

    List<Resultados> findByPartidaId(Long partidaId);


    List<Resultados> findByTorneoId(Long torneoId);
}
