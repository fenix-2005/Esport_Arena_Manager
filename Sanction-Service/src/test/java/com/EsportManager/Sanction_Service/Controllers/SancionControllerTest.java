package com.EsportManager.Sanction_Service.Controllers;

import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Services.SancionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SancionControllerTest {

    @Mock
    private SancionService sancionService;

    @InjectMocks
    private SancionController sancionController;

    @Test
    void emitirSancion() {
        SancionDTO dto = new SancionDTO();
        Sancion sancion = new Sancion();
        when(sancionService.save(any(SancionDTO.class))).thenReturn(sancion);

        ResponseEntity<Sancion> response = sancionController.emitirSancion(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(sancionService, times(1)).save(dto);
    }

    // --- BLOQUE DE PRUEBAS PARA TODOS LOS FILTROS DE LISTAR ---
    @Test
    void listarSancionesPorUsuarioYEstado() {
        when(sancionService.findByUsuarioIdAndEstado(1L, "ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(1L, null, "ACTIVA");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarSancionesPorEquipoYEstado() {
        when(sancionService.findByEquipoIdAndEstado(2L, "ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(null, 2L, "ACTIVA");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarSancionesPorUsuarioId() {
        when(sancionService.findByUsuarioId(1L)).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(1L, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarSancionesPorEquipoId() {
        when(sancionService.findByEquipoId(2L)).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(null, 2L, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarSancionesPorEstado() {
        when(sancionService.findByEstado("ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(null, null, "ACTIVA");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarSancionesSinFiltros() {
        when(sancionService.findAll()).thenReturn(Arrays.asList(new Sancion()));
        ResponseEntity<List<Sancion>> response = sancionController.listarSanciones(null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    // --- FIN BLOQUE DE FILTROS ---

    @Test
    void buscarPorId() {
        Sancion sancion = new Sancion();
        when(sancionService.findById(1L)).thenReturn(sancion);

        ResponseEntity<Sancion> response = sancionController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void verificarSancionActivaConParametros() {
        when(sancionService.tieneSancionActiva(1L, 2L)).thenReturn(true);

        ResponseEntity<Boolean> response = sancionController.verificarSancionActiva(1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void verificarSancionActivaLanzaExcepcion() {
        // Se espera que explote si ambos parámetros son null
        assertThrows(ResponseStatusException.class, () -> {
            sancionController.verificarSancionActiva(null, null);
        });
    }

    @Test
    void actualizarSancion() {
        SancionDTO dto = new SancionDTO();
        Sancion sancion = new Sancion();
        when(sancionService.updateById(any(SancionDTO.class), eq(1L))).thenReturn(sancion);

        ResponseEntity<Sancion> response = sancionController.actualizarSancion(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void cerrarSancion() {
        Sancion sancion = new Sancion();
        when(sancionService.cerrarSancion(1L)).thenReturn(sancion);

        ResponseEntity<Sancion> response = sancionController.cerrarSancion(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void eliminarSancion() {
        doNothing().when(sancionService).deleteById(1L);

        ResponseEntity<Void> response = sancionController.eliminarSancion(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
