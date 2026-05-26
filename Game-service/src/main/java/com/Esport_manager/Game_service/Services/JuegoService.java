package com.Esport_manager.Game_service.Services;

import com.Esport_manager.Game_service.Models.Juego;
import java.util.List;

public interface JuegoService {
    List<Juego> findAll();
    Juego findById(Long id);
    Juego save(Juego juego);
    Juego updateById(Juego juego, Long id);
    void deleteById(Long id);
}
