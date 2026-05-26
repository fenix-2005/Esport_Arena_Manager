package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Models.Torneo;
import java.util.List;

public interface TorneoService {
    List<Torneo> findAll();
    Torneo findById(Long id);
    Torneo save(Torneo torneo);
    Torneo updateById(Torneo torneo, Long id);
    void deleteById(Long id);
}
