package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Models.Inscripcion;
import java.util.List;

public interface InscripcionService {
    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    Inscripcion updateById(Inscripcion inscripcion, Long id);
    void deleteById(Long id);
}
