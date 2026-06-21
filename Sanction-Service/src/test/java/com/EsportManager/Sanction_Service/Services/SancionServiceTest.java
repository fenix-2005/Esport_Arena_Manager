package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Exceptions.SancionNoEncontradaException;
import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Repositories.SancionRepository;
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
public class SancionServiceTest {

    @Mock
    private SancionRepository repository;

    @InjectMocks
    private SancionServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Sancion sancion = new Sancion();
        when(repository.findById(1L)).thenReturn(Optional.of(sancion));

        Sancion resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SancionNoEncontradaException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        SancionDTO dto = new SancionDTO();
        Sancion sancion = new Sancion();

        when(repository.save(any(Sancion.class))).thenReturn(sancion);

        Sancion resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Sancion.class));
    }

    @Test
    void updateByIdExitoso() {
        SancionDTO dto = new SancionDTO();
        Sancion sancion = new Sancion();

        when(repository.findById(1L)).thenReturn(Optional.of(sancion));
        when(repository.save(any(Sancion.class))).thenReturn(sancion);

        Sancion resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        SancionDTO dto = new SancionDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SancionNoEncontradaException.class, () -> {
            service.updateById(dto, 99L);
        });
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByUsuarioIdAndEstado() {
        when(repository.findByUsuarioIdAndEstado(1L, "ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findByUsuarioIdAndEstado(1L, "ACTIVA");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEquipoIdAndEstado() {
        when(repository.findByEquipoIdAndEstado(2L, "ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findByEquipoIdAndEstado(2L, "ACTIVA");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByUsuarioId() {
        when(repository.findByUsuarioId(1L)).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findByUsuarioId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEquipoId() {
        when(repository.findByEquipoId(2L)).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findByEquipoId(2L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("ACTIVA")).thenReturn(Arrays.asList(new Sancion()));
        List<Sancion> resultado = service.findByEstado("ACTIVA");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void tieneSancionActiva() {
        when(repository.existsByUsuarioIdAndEquipoIdAndEstado(1L, 2L, "ACTIVA")).thenReturn(true);
        boolean resultado = service.tieneSancionActiva(1L, 2L);
        assertTrue(resultado);
    }

    @Test
    void cerrarSancionExitoso() {
        Sancion sancion = new Sancion();
        when(repository.findById(1L)).thenReturn(Optional.of(sancion));
        when(repository.save(any(Sancion.class))).thenReturn(sancion);

        Sancion resultado = service.cerrarSancion(1L);

        assertEquals("CERRADA", resultado.getEstado());
        verify(repository, times(1)).save(sancion);
    }

    @Test
    void cerrarSancionLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SancionNoEncontradaException.class, () -> {
            service.cerrarSancion(99L);
        });
    }
}
