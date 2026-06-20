package com.EsportManager.Notification_Service.Services;

import com.EsportManager.Notification_Service.Exceptions.NotificacionNoEncontradaException;
import com.EsportManager.Notification_Service.Models.Dtos.NotificacionDTO;
import com.EsportManager.Notification_Service.Models.Notificacion;
import com.EsportManager.Notification_Service.Repositories.NotificacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Notificacion()));
        List<Notificacion> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Notificacion notificacion = new Notificacion();
        when(repository.findById(1L)).thenReturn(Optional.of(notificacion));

        Notificacion resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotificacionNoEncontradaException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void saveConLeidaNull() {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setLeida(null); // Probamos la validación del operador ternario
        Notificacion notificacion = new Notificacion();

        when(repository.save(any(Notificacion.class))).thenReturn(notificacion);

        Notificacion resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Notificacion.class));
    }

    @Test
    void saveConLeidaDefinida() {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setLeida(true);
        Notificacion notificacion = new Notificacion();

        when(repository.save(any(Notificacion.class))).thenReturn(notificacion);

        Notificacion resultado = service.save(dto);
        assertNotNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByUsuarioId() {
        when(repository.findByUsuarioId(1L)).thenReturn(Arrays.asList(new Notificacion()));
        List<Notificacion> resultado = service.findByUsuarioId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEquipoId() {
        when(repository.findByEquipoId(2L)).thenReturn(Arrays.asList(new Notificacion()));
        List<Notificacion> resultado = service.findByEquipoId(2L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void marcarComoLeidaExitoso() {
        Notificacion notificacion = new Notificacion();
        notificacion.setLeida(false);

        // Primero busca la notificacion
        when(repository.findById(1L)).thenReturn(Optional.of(notificacion));
        // Luego la guarda actualizada
        when(repository.save(any(Notificacion.class))).thenReturn(notificacion);

        Notificacion resultado = service.marcarComoLeida(1L);

        assertNotNull(resultado);
        verify(repository, times(1)).save(notificacion);
    }
}