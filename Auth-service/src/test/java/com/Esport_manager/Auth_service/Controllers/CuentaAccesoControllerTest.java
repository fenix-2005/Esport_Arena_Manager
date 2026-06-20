package com.Esport_manager.Auth_service.Controllers;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Services.CuentaAccesoService;
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
public class CuentaAccesoControllerTest {

    @Mock
    private CuentaAccesoService cuentaAccesoService;

    @InjectMocks
    private CuentaAccesoController cuentaAccesoController;

    @Test
    void crearCuenta() {
        CuentaAccesoDTO dto = new CuentaAccesoDTO();
        CuentaAcceso cuenta = new CuentaAcceso();
        when(cuentaAccesoService.save(any(CuentaAccesoDTO.class))).thenReturn(cuenta);

        ResponseEntity<CuentaAcceso> response = cuentaAccesoController.crearCuenta(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cuentaAccesoService, times(1)).save(dto);
    }

    @Test
    void listarCuentasSinFiltro() {
        when(cuentaAccesoService.findAll()).thenReturn(Arrays.asList(new CuentaAcceso()));

        ResponseEntity<List<CuentaAcceso>> response = cuentaAccesoController.listarCuentas(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void listarCuentasPorRol() {
        when(cuentaAccesoService.findByRol("ADMIN")).thenReturn(Arrays.asList(new CuentaAcceso()));

        ResponseEntity<List<CuentaAcceso>> response = cuentaAccesoController.listarCuentas("ADMIN", null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listarCuentasPorEstado() {
        when(cuentaAccesoService.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new CuentaAcceso()));

        ResponseEntity<List<CuentaAcceso>> response = cuentaAccesoController.listarCuentas(null, "ACTIVO");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorId() {
        CuentaAcceso cuenta = new CuentaAcceso();
        when(cuentaAccesoService.findById(1L)).thenReturn(cuenta);

        ResponseEntity<CuentaAcceso> response = cuentaAccesoController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorCorreo() {
        CuentaAcceso cuenta = new CuentaAcceso();
        when(cuentaAccesoService.findByEmail("test@test.cl")).thenReturn(cuenta);

        ResponseEntity<CuentaAcceso> response = cuentaAccesoController.buscarPorCorreo("test@test.cl");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizarCuenta() {
        CuentaAccesoDTO dto = new CuentaAccesoDTO();
        CuentaAcceso cuenta = new CuentaAcceso();
        when(cuentaAccesoService.updateById(any(CuentaAccesoDTO.class), eq(1L))).thenReturn(cuenta);

        ResponseEntity<CuentaAcceso> response = cuentaAccesoController.actualizarCuenta(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void desactivarCuenta() {
        doNothing().when(cuentaAccesoService).deleteById(1L);

        ResponseEntity<Void> response = cuentaAccesoController.desactivarCuenta(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cuentaAccesoService, times(1)).deleteById(1L);
    }
}