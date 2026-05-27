package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import java.util.List;

public interface EquipoService {
    List<Equipo> findAll();
    Equipo findById(Long id);
    Equipo save(EquipoDTO equipoDTO);
    Equipo updateById(EquipoDTO equipoDTO, Long id);
    void deleteById(Long id);

    List<Equipo> findByJuegoPrincipalId(Long juegoPrincipalId);

    List<Equipo> findByCapitanId(Long capitanId);

    List<Equipo> findByEstado(String estado);
}
