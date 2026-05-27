package com.Esport_manager.Auth_service.Clients;

import com.Esport_manager.Auth_service.Models.Dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El atributo 'name' debe coincidir con el nombre del User-service en Eureka o application.yml
@FeignClient(name = "user-service", path = "/api/v1/usuarios")
public interface UsuarioClient {

    // Necesitamos consultar al User-service por el correo del usuario
    @GetMapping("/email/{email}")
    UsuarioDTO obtenerUsuarioPorEmail(@PathVariable("email") String email);
}
