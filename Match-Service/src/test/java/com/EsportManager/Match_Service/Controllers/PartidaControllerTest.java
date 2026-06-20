package com.EsportManager.Match_Service.Controllers;

import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Services.PartidaService;
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
public class PartidaControllerTest {

    @Mock
    private PartidaService partidaService;

    @InjectMocks
    private PartidaController partidaController;

    @Test
    void crearPartida() {
        PartidaDTO dto = new PartidaDTO();
        Partida partida = new Partida();
        when(partidaService.save(any(PartidaDTO.class))).thenReturn(partida);

        ResponseEntity<Partida> response = partidaController.crearPartida(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(partidaService, times(1)).save(dto);
    }

    @Test
    void listarPartidasSinFiltros() {
        when(partidaService.findAll()).thenReturn(Arrays.asList(new Partida()));

        ResponseEntity<List<Partida>> response = partidaController.listarPartidas(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).findAll();
    }

    @Test
    void listarPartidasPorTorneoId() {
        when(partidaService.findByTorneoId(1L)).thenReturn(Arrays.asList(new Partida()));

        ResponseEntity<List<Partida>> response = partidaController.listarPartidas(1L, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).findByTorneoId(1L);
    }

    @Test
    void listarPartidasPorEstado() {
        when(partidaService.findByEstado("PENDIENTE")).thenReturn(Arrays.asList(new Partida()));

        ResponseEntity<List<Partida>> response = partidaController.listarPartidas(null, "PENDIENTE", null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).findByEstado("PENDIENTE");
    }

    @Test
    void listarPartidasPorRonda() {
        when(partidaService.findByRonda(3)).thenReturn(Arrays.asList(new Partida()));

        ResponseEntity<List<Partida>> response = partidaController.listarPartidas(null, null, 3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).findByRonda(3);
    }

    @Test
    void buscarPorId() {
        Partida partida = new Partida();
        when(partidaService.findById(1L)).thenReturn(partida);

        ResponseEntity<Partida> response = partidaController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).findById(1L);
    }

    @Test
    void actualizarPartida() {
        PartidaDTO dto = new PartidaDTO();
        Partida partida = new Partida();
        when(partidaService.updateById(any(PartidaDTO.class), eq(1L))).thenReturn(partida);

        ResponseEntity<Partida> response = partidaController.actualizarPartida(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partidaService, times(1)).updateById(dto, 1L);
    }

    @Test
    void cancelarPartida() {
        doNothing().when(partidaService).deleteById(1L);

        ResponseEntity<Void> response = partidaController.cancelarPartida(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(partidaService, times(1)).deleteById(1L);
    }
}
