package com.EsportManager.Result_Service.Controllers;

import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Services.ResultadosService;
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
public class ResultadosControllerTest {

    @Mock
    private ResultadosService resultadosService;

    @InjectMocks
    private ResultadosController resultadosController;

    @Test
    void registrarResultado() {
        ResultadosDTO dto = new ResultadosDTO();
        Resultados resultados = new Resultados();
        when(resultadosService.save(any(ResultadosDTO.class))).thenReturn(resultados);

        ResponseEntity<Resultados> response = resultadosController.registrarResultado(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(resultadosService, times(1)).save(dto);
    }

    @Test
    void listarResultadosSinFiltros() {
        when(resultadosService.findAll()).thenReturn(Arrays.asList(new Resultados()));

        ResponseEntity<List<Resultados>> response = resultadosController.listarResultados(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resultadosService, times(1)).findAll();
    }

    @Test
    void listarResultadosPorTorneoId() {
        when(resultadosService.findByTorneoId(1L)).thenReturn(Arrays.asList(new Resultados()));

        ResponseEntity<List<Resultados>> response = resultadosController.listarResultados(1L, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resultadosService, times(1)).findByTorneoId(1L);
    }

    @Test
    void listarResultadosPorPartidaId() {
        when(resultadosService.findByPartidaId(2L)).thenReturn(Arrays.asList(new Resultados()));

        ResponseEntity<List<Resultados>> response = resultadosController.listarResultados(null, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resultadosService, times(1)).findByPartidaId(2L);
    }

    @Test
    void buscarPorIdEncontrado() {
        Resultados resultados = new Resultados();
        when(resultadosService.findById(1L)).thenReturn(resultados);

        ResponseEntity<Resultados> response = resultadosController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resultadosService, times(1)).findById(1L);
    }

    @Test
    void buscarPorIdNoEncontrado() {
        when(resultadosService.findById(99L)).thenReturn(null);

        ResponseEntity<Resultados> response = resultadosController.buscarPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(resultadosService, times(1)).findById(99L);
    }

    @Test
    void actualizarResultado() {
        ResultadosDTO dto = new ResultadosDTO();
        Resultados resultados = new Resultados();
        when(resultadosService.updateById(any(ResultadosDTO.class), eq(1L))).thenReturn(resultados);

        // Simulamos pasar el rolUsuario como un String cualquiera
        ResponseEntity<Resultados> response = resultadosController.actualizarResultado(1L, dto, "ADMIN");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resultadosService, times(1)).updateById(dto, 1L);
    }

    @Test
    void anularResultado() {
        doNothing().when(resultadosService).deleteById(1L);

        ResponseEntity<Void> response = resultadosController.anularResultado(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(resultadosService, times(1)).deleteById(1L);
    }
}
