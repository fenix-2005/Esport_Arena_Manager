package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import java.util.List;

public interface SancionService {
    List<Sancion> findAll();
    Sancion findById(Long id);
    Sancion save(SancionDTO sancionDTO);
    Sancion updateById(SancionDTO sancionDTO, Long id);
    void deleteById(Long id);
    List<Sancion> findByUsuarioIdAndEstado(Long usuarioId, String estado);
    List<Sancion> findByEquipoIdAndEstado(Long equipoId, String estado);
    List<Sancion> findByUsuarioId(Long usuarioId);
    List<Sancion> findByEquipoId(Long equipoId);
    List<Sancion> findByEstado(String estado);
    boolean tieneSancionActiva(Long usuarioId, Long equipoId);
    Sancion cerrarSancion(Long id);
}
