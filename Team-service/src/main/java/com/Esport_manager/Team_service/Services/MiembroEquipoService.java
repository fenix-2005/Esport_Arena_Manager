package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.MiembroEquipo;
import java.util.List;

public interface MiembroEquipoService {
    List<MiembroEquipo> findAll();
    MiembroEquipo findById(Long id);
    MiembroEquipo save(MiembroEquipo miembroEquipo);
    MiembroEquipo updateById(MiembroEquipo miembroEquipo, Long id);
    void deleteById(Long id);
}
