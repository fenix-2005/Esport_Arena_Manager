package com.EsportManager.Match_Service.Services;

import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
import java.util.List;

public interface PartidaService {
    List<Partida> findAll();
    Partida findById(Long id);
    Partida save(PartidaDTO partidaDTO);
    Partida updateById(PartidaDTO partidaDTO, Long id);
    void deleteById(Long id);
    List<Partida> findByTorneoId(Long torneoId);
    List<Partida> findByEstado(String estado);
    List<Partida> findByRonda(Integer ronda);
}
