package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Models.Inscripcion;
import java.util.List;

public interface InscripcionService {
    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(InscripcionDTO inscripcionDTO);
    Inscripcion updateById(InscripcionDTO inscripcionDTO, Long id);
    void deleteById(Long id);
    List<Inscripcion> findByTorneoId(Long torneoId);
    List<Inscripcion> findByEquipoId(Long equipoId);
    List<Inscripcion> findByJugadorId(Long jugadorId);
}
