package com.Esport_manager.Auth_service.Repositories;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaAccesoRepository extends JpaRepository<CuentaAcceso, Long> {
    //Busca por email y manda nada si esta vacio
    Optional<CuentaAcceso>findByEmail(String email);

    //Lista todos los usuarios por Rol
    List<CuentaAcceso> findByRol(String rol);

    //Lista todos los usuarios por Estado
    List<CuentaAcceso> findByEstado(String estado);
}
