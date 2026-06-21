package com.Esport_manager.Team_service.Controllers;

import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Services.EquipoService;
import com.Esport_manager.Team_service.Services.MiembroEquipoService;
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
public class EquipoControllerTest {

    @Mock
    private EquipoService equipoService;

    @Mock
    private MiembroEquipoService miembroService;

    @InjectMocks
    private EquipoController equipoController;

    @Test
    void crearEquipo() {
        EquipoDTO dto = new EquipoDTO();
        Equipo equipo = new Equipo();
        when(equipoService.save(any(EquipoDTO.class))).thenReturn(equipo);

        ResponseEntity<Equipo> response = equipoController.crearEquipo(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(equipoService, times(1)).save(dto);
    }

    @Test
    void listarEquiposPorJuegoPrincipalId() {
        when(equipoService.findByJuegoPrincipalId(1L)).thenReturn(Arrays.asList(new Equipo()));
        ResponseEntity<List<Equipo>> response = equipoController.listarEquipos(1L, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).findByJuegoPrincipalId(1L);
    }

    @Test
    void listarEquiposPorCapitanId() {
        when(equipoService.findByCapitanId(2L)).thenReturn(Arrays.asList(new Equipo()));
        ResponseEntity<List<Equipo>> response = equipoController.listarEquipos(null, 2L, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).findByCapitanId(2L);
    }

    @Test
    void listarEquiposPorEstado() {
        when(equipoService.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Equipo()));
        ResponseEntity<List<Equipo>> response = equipoController.listarEquipos(null, null, "ACTIVO");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).findByEstado("ACTIVO");
    }

    @Test
    void listarEquiposSinFiltros() {
        when(equipoService.findAll()).thenReturn(Arrays.asList(new Equipo()));
        ResponseEntity<List<Equipo>> response = equipoController.listarEquipos(null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).findAll();
    }

    @Test
    void buscarEquipoPorIdEncontrado() {
        Equipo equipo = new Equipo();
        when(equipoService.findById(1L)).thenReturn(equipo);

        ResponseEntity<Equipo> response = equipoController.buscarEquipoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).findById(1L);
    }

    @Test
    void buscarEquipoPorIdNoEncontrado() {
        when(equipoService.findById(99L)).thenReturn(null);

        ResponseEntity<Equipo> response = equipoController.buscarEquipoPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(equipoService, times(1)).findById(99L);
    }

    @Test
    void actualizarEquipo() {
        EquipoDTO dto = new EquipoDTO();
        Equipo equipo = new Equipo();
        when(equipoService.updateById(any(EquipoDTO.class), eq(1L))).thenReturn(equipo);

        ResponseEntity<Equipo> response = equipoController.actualizarEquipo(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(equipoService, times(1)).updateById(dto, 1L);
    }

    @Test
    void eliminarEquipo() {
        doNothing().when(equipoService).deleteById(1L);

        ResponseEntity<Void> response = equipoController.eliminarEquipo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(equipoService, times(1)).deleteById(1L);
    }

    @Test
    void agregarMiembro() {
        MiembroEquipoDTO dto = new MiembroEquipoDTO();
        MiembroEquipo miembro = new MiembroEquipo();
        when(miembroService.save(any(MiembroEquipoDTO.class))).thenReturn(miembro);

        ResponseEntity<MiembroEquipo> response = equipoController.agregarMiembro(1L, dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, dto.getEquipoId());
        verify(miembroService, times(1)).save(dto);
    }

    @Test
    void listarMiembrosDeEquipo() {
        when(miembroService.findByEquipoId(1L)).thenReturn(Arrays.asList(new MiembroEquipo()));

        ResponseEntity<List<MiembroEquipo>> response = equipoController.listarMiembrosDeEquipo(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(miembroService, times(1)).findByEquipoId(1L);
    }

    @Test
    void expulsarMiembro() {
        doNothing().when(miembroService).eliminarPorEquipoYUsuario(1L, 2L);

        ResponseEntity<Void> response = equipoController.expulsarMiembro(1L, 2L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(miembroService, times(1)).eliminarPorEquipoYUsuario(1L, 2L);
    }
}
