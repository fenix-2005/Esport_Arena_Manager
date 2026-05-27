package com.Esport_manager.Tournament_service.Clients;

import com.Esport_manager.Tournament_service.Models.Dtos.JuegoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", path = "/api/v1/juegos")
public interface JuegoClient {
    // Valida que el juego exista y esté activo antes de crear un torneo para él
    @GetMapping("/{id}")
    JuegoDTO obtenerJuegoPorId(@PathVariable("id") Long id);
}
