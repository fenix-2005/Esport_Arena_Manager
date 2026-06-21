package com.Esport_manager.Tournament_service.Controllers;

import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;
import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Services.TorneoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TorneoControllerTest {

    @Mock
    private TorneoService torneoService;

    @InjectMocks
    private TorneoController torneoController;

    @Test
    void crearTorneo() {
        TorneoDTO dto = new TorneoDTO();
        Torneo torneo = new Torneo();
        when(torneoService.save(any(TorneoDTO.class))).thenReturn(torneo);

        ResponseEntity<Torneo> response = torneoController.crearTorneo(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(torneoService, times(1)).save(dto);
    }

    @Test
    void listarTorneosPorJuegoId() {
        when(torneoService.findByJuegoId(1L)).thenReturn(Arrays.asList(new Torneo()));
        ResponseEntity<List<Torneo>> response = torneoController.listarTorneos(1L, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).findByJuegoId(1L);
    }

    @Test
    void listarTorneosPorEstado() {
        when(torneoService.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Torneo()));
        ResponseEntity<List<Torneo>> response = torneoController.listarTorneos(null, "ACTIVO", null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).findByEstado("ACTIVO");
    }

    @Test
    void listarTorneosPorFechaInicio() {
        LocalDate fecha = LocalDate.now();
        when(torneoService.findByFechaInicio(fecha)).thenReturn(Arrays.asList(new Torneo()));
        ResponseEntity<List<Torneo>> response = torneoController.listarTorneos(null, null, fecha);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).findByFechaInicio(fecha);
    }

    @Test
    void listarTorneosSinFiltros() {
        when(torneoService.findAll()).thenReturn(Arrays.asList(new Torneo()));
        ResponseEntity<List<Torneo>> response = torneoController.listarTorneos(null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).findAll();
    }

    @Test
    void buscarPorId() {
        Torneo torneo = new Torneo();
        when(torneoService.findById(1L)).thenReturn(torneo);

        ResponseEntity<Torneo> response = torneoController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).findById(1L);
    }

    @Test
    void actualizarTorneo() {
        TorneoDTO dto = new TorneoDTO();
        Torneo torneo = new Torneo();
        when(torneoService.updateById(any(TorneoDTO.class), eq(1L))).thenReturn(torneo);

        ResponseEntity<Torneo> response = torneoController.actualizarTorneo(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(torneoService, times(1)).updateById(dto, 1L);
    }

    @Test
    void cancelarTorneo() {
        doNothing().when(torneoService).deleteById(1L);

        ResponseEntity<Void> response = torneoController.cancelarTorneo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(torneoService, times(1)).deleteById(1L);
    }
}
