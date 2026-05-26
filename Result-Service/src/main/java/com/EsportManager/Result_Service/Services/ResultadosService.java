package com.EsportManager.Result_Service.Services;

import com.EsportManager.Result_Service.Models.Resultados;
import java.util.List;

public interface ResultadosService {
    List<Resultados> findAll();
    Resultados findById(Long id);
    Resultados save(Resultados resultados);
    Resultados updateById(Resultados resultados, Long id);
    void deleteById(Long id);
}
