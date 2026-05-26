package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.Premio;
import java.util.List;

public interface PremioService {
    List<Premio> findAll();
    Premio findById(Long id);
    Premio save(Premio premio);
    Premio updateById(Premio premio, Long id);
    void deleteById(Long id);
}
