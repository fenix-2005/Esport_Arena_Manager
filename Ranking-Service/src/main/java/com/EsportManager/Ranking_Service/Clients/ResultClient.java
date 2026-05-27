package com.EsportManager.Ranking_Service.Clients;

import com.EsportManager.Ranking_Service.Models.Dtos.ResultadosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "result-service", path = "/api/v1/resultados")
public interface ResultClient {
    // Extrae los resultados validados de un torneo para recalcular los puntos
    @GetMapping
    List<ResultadosDTO> obtenerResultadosPorTorneo(@RequestParam("torneoId") Long torneoId);
}
