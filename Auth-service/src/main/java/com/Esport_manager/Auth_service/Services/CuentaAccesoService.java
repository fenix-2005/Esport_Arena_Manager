package com.Esport_manager.Auth_service.Services;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import java.util.List;

public interface CuentaAccesoService {
    List<CuentaAcceso> findAll();
    CuentaAcceso findById(Long id);
    CuentaAcceso save(CuentaAcceso cuentaAcceso);
    CuentaAcceso updateById(CuentaAcceso cuentaAcceso, Long id);
    void deleteById(Long id);
}
