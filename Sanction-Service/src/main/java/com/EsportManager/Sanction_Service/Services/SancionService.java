package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Models.Sancion;
import java.util.List;

public interface SancionService {
    List<Sancion> findAll();
    Sancion findById(Long id);
    Sancion save(Sancion sancion);
    Sancion updateById(Sancion sancion, Long id);
    void deleteById(Long id);
}
