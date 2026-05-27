package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import java.util.List;

public interface PremioAsignadoService {
    List<PremioAsignado> findAll();
    PremioAsignado findById(Long id);
    PremioAsignado save(PremioAsignadoDTO premioAsignadoDTO);
    PremioAsignado updateById(PremioAsignadoDTO premioAsignadoDTO, Long id);
    void deleteById(Long id);
    List<PremioAsignado> findByParticipanteId(Long participanteId);
}
