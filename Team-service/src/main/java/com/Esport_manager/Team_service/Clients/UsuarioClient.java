package com.Esport_manager.Team_service.Clients;

import com.Esport_manager.Team_service.Models.Dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/usuarios")
public interface UsuarioClient {
    // Valida que el usuario (Capitán o Miembro) exista en el sistema antes de asignarlo
    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") Long id);
}
