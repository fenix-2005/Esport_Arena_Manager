package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;

import java.time.LocalDate;
import java.util.List;

public interface TorneoService {
    List<Torneo> findAll();
    Torneo findById(Long id);
    Torneo save(TorneoDTO torneoDTO);
    Torneo updateById(TorneoDTO torneoDTO, Long id);
    void deleteById(Long id);
    List<Torneo> findByJuegoId(Long juegoId);
    List<Torneo> findByEstado(String estado);
    List<Torneo> findByFechaInicio(LocalDate fechaInicio);
}
