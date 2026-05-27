package com.EsportManager.Result_Service.Clients;

import com.EsportManager.Result_Service.Models.Dtos.PartidaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "match-service", path = "/api/v1/partidas")
public interface PartidaClient {
    // Valida que la partida exista antes de intentar registrar un resultado
    @GetMapping("/{id}")
    PartidaDTO obtenerPartidaPorId(@PathVariable("id") Long id);
}
