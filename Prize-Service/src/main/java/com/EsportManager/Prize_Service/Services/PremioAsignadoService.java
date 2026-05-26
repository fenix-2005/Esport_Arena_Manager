package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.PremioAsignado;
import java.util.List;

public interface PremioAsignadoService {
    List<PremioAsignado> findAll();
    PremioAsignado findById(Long id);
    PremioAsignado save(PremioAsignado premioAsignado);
    PremioAsignado updateById(PremioAsignado premioAsignado, Long id);
    void deleteById(Long id);
}
