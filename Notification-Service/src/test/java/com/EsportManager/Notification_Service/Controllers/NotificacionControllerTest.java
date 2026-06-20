package com.EsportManager.Notification_Service.Controllers;

import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Services.NotificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionControllerTest {

    @Mock
    private NotificacionService notificacionService;

    @InjectMocks
    private NotificacionController notificacionController;

    @Test
    void crearNotificacion() {
        NotificacionDTO dto = new NotificacionDTO();
        Notificacion notificacion = new Notificacion();
        when(notificacionService.save(any(NotificacionDTO.class))).thenReturn(notificacion);

        ResponseEntity<Notificacion> response = notificacionController.crearNotificacion(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(notificacionService, times(1)).save(dto);
    }

    @Test
    void listarNotificacionesSinFiltros() {
        when(notificacionService.findAll()).thenReturn(Arrays.asList(new Notificacion()));

        ResponseEntity<List<Notificacion>> response = notificacionController.listarNotificaciones(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).findAll();
    }

    @Test
    void listarNotificacionesPorUsuarioId() {
        when(notificacionService.findByUsuarioId(1L)).thenReturn(Arrays.asList(new Notificacion()));

        ResponseEntity<List<Notificacion>> response = notificacionController.listarNotificaciones(1L, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).findByUsuarioId(1L);
    }

    @Test
    void listarNotificacionesPorEquipoId() {
        when(notificacionService.findByEquipoId(2L)).thenReturn(Arrays.asList(new Notificacion()));

        ResponseEntity<List<Notificacion>> response = notificacionController.listarNotificaciones(null, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).findByEquipoId(2L);
    }

    @Test
    void buscarPorId() {
        Notificacion notificacion = new Notificacion();
        when(notificacionService.findById(1L)).thenReturn(notificacion);

        ResponseEntity<Notificacion> response = notificacionController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).findById(1L);
    }

    @Test
    void marcarComoLeida() {
        Notificacion notificacion = new Notificacion();
        when(notificacionService.marcarComoLeida(1L)).thenReturn(notificacion);

        ResponseEntity<Notificacion> response = notificacionController.marcarComoLeida(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).marcarComoLeida(1L);
    }

    @Test
    void archivarNotificacion() {
        doNothing().when(notificacionService).deleteById(1L);

        ResponseEntity<Void> response = notificacionController.archivarNotificacion(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(notificacionService, times(1)).deleteById(1L);
    }
}
