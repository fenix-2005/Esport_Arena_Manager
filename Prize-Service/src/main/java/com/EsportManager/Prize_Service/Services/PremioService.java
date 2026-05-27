package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
import com.EsportManager.Prize_Service.Models.Premio;
import java.util.List;

public interface PremioService {
    List<Premio> findAll();
    Premio findById(Long id);
    Premio save(PremioDTO premioDTO);
    Premio updateById(PremioDTO premioDTO, Long id);
    void deleteById(Long id);
    List<Premio> findByTorneoId(Long torneoId);
    List<Premio> findByPosicion(Integer posicion);
}
