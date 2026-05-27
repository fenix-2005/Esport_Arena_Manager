package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
import java.util.List;

public interface MiembroEquipoService {
    List<MiembroEquipo> findAll();
    MiembroEquipo findById(Long id);
    MiembroEquipo save(MiembroEquipoDTO dto);
    MiembroEquipo updateById(MiembroEquipoDTO dto, Long id);
    void deleteById(Long id);
    List<MiembroEquipo> findByEquipoId(Long equipoId);
    void eliminarPorEquipoYUsuario(Long equipoId, Long usuarioId);
}
