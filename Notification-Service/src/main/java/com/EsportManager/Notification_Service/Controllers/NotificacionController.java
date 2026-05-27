package com.EsportManager.Notification_Service.Controllers;

import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // 1. Crear notificación
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        Notificacion nuevaNotificacion = notificacionService.save(dto);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    // 2. Listar notificaciones (Filtradas por usuario o equipo)
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

    // 3. Buscar notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.findById(id);
        return new ResponseEntity<>(notificacion, HttpStatus.OK);
    }

    // 4. Actualizar estado a Leída
    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        Notificacion notificacionLeida = notificacionService.marcarComoLeida(id);
        return new ResponseEntity<>(notificacionLeida, HttpStatus.OK);
    }

    // 5. AEliminar notificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> archivarNotificacion(@PathVariable Long id) {
        notificacionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}