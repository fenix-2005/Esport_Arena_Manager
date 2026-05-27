package com.EsportManager.Registration_Service.Clients;

import com.EsportManager.Registration_Service.Models.Dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/usuarios")
public interface UsuarioClient {
    // Si la inscripción es individual, valida que el jugador exista y esté activo
    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") Long id);
}


