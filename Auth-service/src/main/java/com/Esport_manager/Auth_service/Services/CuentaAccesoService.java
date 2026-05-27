package com.Esport_manager.Auth_service.Services;

import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import java.util.List;

public interface CuentaAccesoService {
    List<CuentaAcceso> findAll();
    CuentaAcceso findById(Long id);
    CuentaAcceso save(CuentaAccesoDTO cuentaAccesoDTO);
    CuentaAcceso updateById(CuentaAccesoDTO cuentaAccesoDTO, Long id);
    void deleteById(Long id);
    List<CuentaAcceso> findByRol(String rol);

    List<CuentaAcceso> findByEstado(String estado);

    CuentaAcceso findByEmail(String email);
}
