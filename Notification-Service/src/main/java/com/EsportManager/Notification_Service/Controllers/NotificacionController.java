package com.EsportManager.Notification_Service.Controllers;

import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Services.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Alertas y Notificaciones", description = "Control de mensajería directa y alertas automáticas a competidores")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Despachar nueva notificación", description = "Crea e inyecta un aviso directo al buzón personal de un usuario o de una escuadra completa.")
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        Notificacion nuevaNotificacion = notificacionService.save(dto);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar buzón de alertas", description = "Obtiene los mensajes enviados, permitiendo filtrar la bandeja de entrada por usuario o por equipo.")
    @ApiResponse(responseCode = "200", description = "Bandeja histórica recuperada correctamente")
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarNotificaciones(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long equipoId) {
        List<Notificacion> notificaciones;
        if (usuarioId != null) {
            notificaciones = notificacionService.findByUsuarioId(usuarioId);
        } else if (equipoId != null) {
            notificaciones = notificacionService.findByEquipoId(equipoId);
        } else {
            notificaciones = notificacionService.findAll();
        }
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @Operation(summary = "Consultar notificación por ID", description = "Obtiene los parámetros detallados de una alerta guardada.")
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.findById(id);
        return new ResponseEntity<>(notificacion, HttpStatus.OK);
    }

    @Operation(summary = "Marcar alerta como leída", description = "Actualiza el estado de lectura de una alerta para limpiar la interfaz del usuario.")
    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        Notificacion notificacionLeida = notificacionService.marcarComoLeida(id);
        return new ResponseEntity<>(notificacionLeida, HttpStatus.OK);
    }

    @Operation(summary = "Archivar/Eliminar notificación", description = "Remueve de forma lógica o física un aviso del historial de la aplicación.")
    @ApiResponse(responseCode = "204", description = "Aviso removido del buzón")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> archivarNotificacion(@PathVariable Long id) {
        notificacionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}