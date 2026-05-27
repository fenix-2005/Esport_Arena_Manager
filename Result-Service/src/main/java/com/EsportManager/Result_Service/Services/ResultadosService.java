package com.EsportManager.Result_Service.Services;

import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Models.Resultados;
import java.util.List;

public interface ResultadosService {
    List<Resultados> findAll();
    Resultados findById(Long id);
    Resultados save(ResultadosDTO resultadosDTO);
    Resultados updateById(ResultadosDTO resultadosDTO, Long id);
    void deleteById(Long id);
    List<Resultados> findByTorneoId(Long torneoId);
    List<Resultados> findByPartidaId(Long partidaId);
}
