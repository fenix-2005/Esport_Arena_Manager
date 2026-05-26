package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.Equipo;
import java.util.List;

public interface EquipoService {
    List<Equipo> findAll();
    Equipo findById(Long id);
    Equipo save(Equipo equipo);
    Equipo updateById(Equipo equipo, Long id);
    void deleteById(Long id);
}
