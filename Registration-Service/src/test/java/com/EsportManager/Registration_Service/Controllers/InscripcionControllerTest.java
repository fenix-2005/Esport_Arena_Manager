package com.EsportManager.Registration_Service.Controllers;

import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Services.InscripcionService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InscripcionControllerTest {

    @Mock
    private InscripcionService inscripcionService;

    @InjectMocks
    private InscripcionController inscripcionController;

    @Test
    void crearInscripcion() {
        InscripcionDTO dto = new InscripcionDTO();
        Inscripcion inscripcion = new Inscripcion();
        when(inscripcionService.save(any(InscripcionDTO.class))).thenReturn(inscripcion);

        ResponseEntity<Inscripcion> response = inscripcionController.crearInscripcion(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(inscripcionService, times(1)).save(dto);
    }

    @Test
    void listarInscripcionesSinFiltros() {
        when(inscripcionService.findAll()).thenReturn(Arrays.asList(new Inscripcion()));

        ResponseEntity<List<Inscripcion>> response = inscripcionController.listarInscripciones(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).findAll();
    }

    @Test
    void listarInscripcionesPorTorneo() {
        when(inscripcionService.findByTorneoId(1L)).thenReturn(Arrays.asList(new Inscripcion()));

        ResponseEntity<List<Inscripcion>> response = inscripcionController.listarInscripciones(1L, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).findByTorneoId(1L);
    }

    @Test
    void listarInscripcionesPorEquipo() {
        when(inscripcionService.findByEquipoId(2L)).thenReturn(Arrays.asList(new Inscripcion()));

        ResponseEntity<List<Inscripcion>> response = inscripcionController.listarInscripciones(null, 2L, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).findByEquipoId(2L);
    }

    @Test
    void listarInscripcionesPorJugador() {
        when(inscripcionService.findByJugadorId(3L)).thenReturn(Arrays.asList(new Inscripcion()));

        ResponseEntity<List<Inscripcion>> response = inscripcionController.listarInscripciones(null, null, 3L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).findByJugadorId(3L);
    }

    @Test
    void buscarPorIdEncontrado() {
        Inscripcion inscripcion = new Inscripcion();
        when(inscripcionService.findById(1L)).thenReturn(inscripcion);

        ResponseEntity<Inscripcion> response = inscripcionController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).findById(1L);
    }

    @Test
    void buscarPorIdNoEncontrado() {
        when(inscripcionService.findById(99L)).thenReturn(null);

        ResponseEntity<Inscripcion> response = inscripcionController.buscarPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(inscripcionService, times(1)).findById(99L);
    }

    @Test
    void actualizarEstadoInscripcion() {
        InscripcionDTO dto = new InscripcionDTO();
        Inscripcion inscripcion = new Inscripcion();
        when(inscripcionService.updateById(any(InscripcionDTO.class), eq(1L))).thenReturn(inscripcion);

        ResponseEntity<Inscripcion> response = inscripcionController.actualizarEstadoInscripcion(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(inscripcionService, times(1)).updateById(dto, 1L);
    }

    @Test
    void cancelarInscripcion() {
        doNothing().when(inscripcionService).deleteById(1L);

        ResponseEntity<Void> response = inscripcionController.cancelarInscripcion(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(inscripcionService, times(1)).deleteById(1L);
    }
}
