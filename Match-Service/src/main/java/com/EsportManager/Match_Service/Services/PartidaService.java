package com.EsportManager.Match_Service.Services;

import com.EsportManager.Match_Service.Models.Partida;
import java.util.List;

public interface PartidaService {
    List<Partida> findAll();
    Partida findById(Long id);
    Partida save(Partida partida);
    Partida updateById(Partida partida, Long id);
    void deleteById(Long id);
}
