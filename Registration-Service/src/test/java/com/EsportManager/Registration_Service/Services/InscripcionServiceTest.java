package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Repositories.InscripcionRepository;
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
public class InscripcionServiceTest {

    @Mock
    private InscripcionRepository repository;

    @InjectMocks
    private InscripcionServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Inscripcion()));
        List<Inscripcion> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Inscripcion inscripcion = new Inscripcion();
        when(repository.findById(1L)).thenReturn(Optional.of(inscripcion));

        Inscripcion resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdRetornaNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Inscripcion resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        InscripcionDTO dto = new InscripcionDTO();
        Inscripcion inscripcion = new Inscripcion();

        when(repository.save(any(Inscripcion.class))).thenReturn(inscripcion);

        Inscripcion resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Inscripcion.class));
    }

    @Test
    void updateByIdExitoso() {
        InscripcionDTO dto = new InscripcionDTO();
        Inscripcion inscripcion = new Inscripcion();

        when(repository.findById(1L)).thenReturn(Optional.of(inscripcion));
        when(repository.save(any(Inscripcion.class))).thenReturn(inscripcion);

        Inscripcion resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdRetornaNull() {
        InscripcionDTO dto = new InscripcionDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Inscripcion resultado = service.updateById(dto, 99L);
        assertNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByTorneoId() {
        when(repository.findByTorneoId(1L)).thenReturn(Arrays.asList(new Inscripcion()));
        List<Inscripcion> resultado = service.findByTorneoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEquipoId() {
        when(repository.findByEquipoId(2L)).thenReturn(Arrays.asList(new Inscripcion()));
        List<Inscripcion> resultado = service.findByEquipoId(2L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByJugadorId() {
        when(repository.findByJugadorId(3L)).thenReturn(Arrays.asList(new Inscripcion()));
        List<Inscripcion> resultado = service.findByJugadorId(3L);
        assertFalse(resultado.isEmpty());
    }
}
