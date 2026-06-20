package com.EsportManager.Prize_Service.Controllers;

import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Services.PremioAsignadoService;
import com.EsportManager.Prize_Service.Services.PremioService;
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
public class PremioControllerTest {

    @Mock
    private PremioService premioService;

    @Mock
    private PremioAsignadoService premioAsignadoService;

    @InjectMocks
    private PremioController premioController;

    @Test
    void crearPremio() {
        PremioDTO dto = new PremioDTO();
        Premio premio = new Premio();
        when(premioService.save(any(PremioDTO.class))).thenReturn(premio);

        ResponseEntity<Premio> response = premioController.crearPremio(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void listarPremiosSinFiltros() {
        when(premioService.findAll()).thenReturn(Arrays.asList(new Premio()));
        ResponseEntity<List<Premio>> response = premioController.listarPremios(null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarPremiosPorTorneoId() {
        when(premioService.findByTorneoId(1L)).thenReturn(Arrays.asList(new Premio()));
        ResponseEntity<List<Premio>> response = premioController.listarPremios(1L, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarPremiosPorPosicion() {
        when(premioService.findByPosicion(1)).thenReturn(Arrays.asList(new Premio()));
        ResponseEntity<List<Premio>> response = premioController.listarPremios(null, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPremioPorId() {
        Premio premio = new Premio();
        when(premioService.findById(1L)).thenReturn(premio);
        ResponseEntity<Premio> response = premioController.buscarPremioPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizarPremio() {
        PremioDTO dto = new PremioDTO();
        Premio premio = new Premio();
        when(premioService.updateById(any(PremioDTO.class), eq(1L))).thenReturn(premio);

        ResponseEntity<Premio> response = premioController.actualizarPremio(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void eliminarPremio() {
        doNothing().when(premioService).deleteById(1L);
        ResponseEntity<Void> response = premioController.eliminarPremio(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void asignarPremio() {
        PremioAsignadoDTO dto = new PremioAsignadoDTO();
        PremioAsignado asignado = new PremioAsignado();
        when(premioAsignadoService.save(any(PremioAsignadoDTO.class))).thenReturn(asignado);

        ResponseEntity<PremioAsignado> response = premioController.asignarPremio(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void listarPremiosAsignadosSinFiltros() {
        when(premioAsignadoService.findAll()).thenReturn(Arrays.asList(new PremioAsignado()));
        ResponseEntity<List<PremioAsignado>> response = premioController.listarPremiosAsignados(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarPremiosAsignadosPorParticipanteId() {
        when(premioAsignadoService.findByParticipanteId(1L)).thenReturn(Arrays.asList(new PremioAsignado()));
        ResponseEntity<List<PremioAsignado>> response = premioController.listarPremiosAsignados(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
