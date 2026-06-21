package com.Esport_manager.Auth_service.Controllers;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Services.CuentaAccesoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas")
@Tag(name = "Cuentas de Acceso", description = "Endpoints oficiales para la gestión de credenciales y perfiles de seguridad")
public class CuentaAccesoController {

    @Autowired
    private CuentaAccesoService cuentaAccesoService;

    @Operation(summary = "Registrar una nueva cuenta", description = "Crea credenciales de acceso vinculadas al correo de un usuario.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cuenta creada correctamente en la base de datos"),
            @ApiResponse(responseCode = "400", description = "Estructura de la petición o datos inválidos")
    })
    @PostMapping
    public ResponseEntity<CuentaAcceso> crearCuenta(@RequestBody CuentaAccesoDTO dto) {
        CuentaAcceso nuevaCuenta = cuentaAccesoService.save(dto);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener listado de cuentas", description = "Recupera todas las cuentas con posibilidad de aplicar filtros por rol o estado de acceso.")
    @ApiResponse(responseCode = "200", description = "Listado de cuentas obtenido exitosamente")
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

    @Operation(summary = "Buscar cuenta por ID", description = "Obtiene los detalles y roles de una credencial específica por su identificador.")
    @ApiResponse(responseCode = "200", description = "Registro de cuenta localizado")
    @GetMapping("/{id}")
    public ResponseEntity<CuentaAcceso> buscarPorId(@PathVariable Long id) {
        CuentaAcceso cuenta = cuentaAccesoService.findById(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @Operation(summary = "Buscar cuenta por Correo Electrónico", description = "Permite a los servicios de autenticación validar la existencia de un usuario mediante su email.")
    @ApiResponse(responseCode = "200", description = "Cuenta asociada al correo localizada")
    @GetMapping("/correo/{email}")
    public ResponseEntity<CuentaAcceso> buscarPorCorreo(@PathVariable String email) {
        CuentaAcceso cuenta = cuentaAccesoService.findByEmail(email);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar datos de cuenta", description = "Modifica campos permitidos como contraseñas, roles asignados o estado operativo.")
    @ApiResponse(responseCode = "200", description = "Datos de acceso actualizados correctamente")
    @PutMapping("/{id}")
    public ResponseEntity<CuentaAcceso> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaAccesoDTO dto) {
        CuentaAcceso cuentaActualizada = cuentaAccesoService.updateById(dto, id);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Desactivar cuenta del sistema", description = "Desactiva los accesos de un usuario de forma segura sin borrar el registro histórico.")
    @ApiResponse(responseCode = "204", description = "Cuenta de usuario desactivada con éxito")
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarCuenta(@PathVariable Long id) {
        cuentaAccesoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}