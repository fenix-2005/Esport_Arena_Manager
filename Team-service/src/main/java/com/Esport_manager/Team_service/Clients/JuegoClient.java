package com.Esport_manager.Team_service.Clients;

import com.Esport_manager.Team_service.Models.Dtos.JuegoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", path = "/api/v1/juegos")
public interface JuegoClient {
    // Valida que el juego al que se inscribe el equipo esté registrado y activo
    @GetMapping("/{id}")
    JuegoDTO obtenerJuegoPorId(@PathVariable("id") Long id);
}
