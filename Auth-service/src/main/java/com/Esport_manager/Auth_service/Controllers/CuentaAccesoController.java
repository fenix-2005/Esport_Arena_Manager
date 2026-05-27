package com.Esport_manager.Auth_service.Controllers;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Services.CuentaAccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaAccesoController {

    @Autowired
    private CuentaAccesoService cuentaAccesoService;

    // 1. Crear cuenta
    @PostMapping
    public ResponseEntity<CuentaAcceso> crearCuenta(@RequestBody CuentaAccesoDTO dto) {
        CuentaAcceso nuevaCuenta = cuentaAccesoService.save(dto);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    // 2. Listar cuentas
    @GetMapping
    public ResponseEntity<List<CuentaAcceso>> listarCuentas(
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String estado) {

        List<CuentaAcceso> cuentas;
        if (rol != null) {
            cuentas = cuentaAccesoService.findByRol(rol);
        } else if (estado != null) {
            cuentas = cuentaAccesoService.findByEstado(estado);
        } else {
            cuentas = cuentaAccesoService.findAll();
        }
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    // 3A. Buscar cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<CuentaAcceso> buscarPorId(@PathVariable Long id) {
        CuentaAcceso cuenta = cuentaAccesoService.findById(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    // 3B. Buscar cuenta por Correo
    @GetMapping("/correo/{email}")
    public ResponseEntity<CuentaAcceso> buscarPorCorreo(@PathVariable String email) {
        CuentaAcceso cuenta = cuentaAccesoService.findByEmail(email);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    // 4. Actualizar contraseña, rol o estado
    @PutMapping("/{id}")
    public ResponseEntity<CuentaAcceso> actualizarCuenta(
            @PathVariable Long id,
            @RequestBody CuentaAccesoDTO dto) {
        CuentaAcceso cuentaActualizada = cuentaAccesoService.updateById(dto, id);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    // 5. Desactivar cuenta
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarCuenta(@PathVariable Long id) {
        cuentaAccesoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
