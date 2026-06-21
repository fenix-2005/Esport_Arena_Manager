package com.Esport_manager.Game_service.Controllers;

import com.Esport_manager.Game_service.Models.Dtos.JuegoDTO;
import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Services.JuegoService;
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
public class JuegoControllerTest {

    @Mock
    private JuegoService juegoService;

    @InjectMocks
    private JuegoController juegoController;

    @Test
    void crearJuego() {
        JuegoDTO dto = new JuegoDTO();
        Juego juego = new Juego();
        when(juegoService.save(any(JuegoDTO.class))).thenReturn(juego);

        ResponseEntity<Juego> response = juegoController.crearJuego(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void listarJuegosSinEstado() {
        when(juegoService.findAll()).thenReturn(Arrays.asList(new Juego()));
        ResponseEntity<List<Juego>> response = juegoController.listarJuegos(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarJuegosConEstado() {
        when(juegoService.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Juego()));
        ResponseEntity<List<Juego>> response = juegoController.listarJuegos("ACTIVO");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorIdEncontrado() {
        Juego juego = new Juego();
        when(juegoService.findById(1L)).thenReturn(juego);
        ResponseEntity<Juego> response = juegoController.buscarPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorIdNoEncontrado() {
        when(juegoService.findById(99L)).thenReturn(null);
        ResponseEntity<Juego> response = juegoController.buscarPorId(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void buscarPorNombreEncontrado() {
        Juego juego = new Juego();
        when(juegoService.findByNombre("LoL")).thenReturn(juego);
        ResponseEntity<Juego> response = juegoController.buscarPorNombre("LoL");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorNombreNoEncontrado() {
        when(juegoService.findByNombre("Nulo")).thenReturn(null);
        ResponseEntity<Juego> response = juegoController.buscarPorNombre("Nulo");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void actualizarJuegoEncontrado() {
        JuegoDTO dto = new JuegoDTO();
        Juego juego = new Juego();
        when(juegoService.updateById(any(JuegoDTO.class), eq(1L))).thenReturn(juego);

        ResponseEntity<Juego> response = juegoController.actualizarJuego(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizarJuegoNoEncontrado() {
        JuegoDTO dto = new JuegoDTO();
        when(juegoService.updateById(any(JuegoDTO.class), eq(99L))).thenReturn(null);

        ResponseEntity<Juego> response = juegoController.actualizarJuego(99L, dto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void eliminarJuego() {
        doNothing().when(juegoService).deleteById(1L);
        ResponseEntity<Void> response = juegoController.eliminarJuego(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(juegoService, times(1)).deleteById(1L);
    }
}
