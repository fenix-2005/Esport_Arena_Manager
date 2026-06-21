package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Repositories.EquipoRepository;
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
public class EquipoServiceTest {

    @Mock
    private EquipoRepository repository;

    @InjectMocks
    private EquipoServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Equipo()));
        List<Equipo> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Equipo equipo = new Equipo();
        when(repository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdRetornaNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Equipo resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        EquipoDTO dto = new EquipoDTO();
        Equipo equipo = new Equipo();

        when(repository.save(any(Equipo.class))).thenReturn(equipo);

        Equipo resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Equipo.class));
    }

    @Test
    void updateByIdExitoso() {
        EquipoDTO dto = new EquipoDTO();
        Equipo equipo = new Equipo();

        when(repository.findById(1L)).thenReturn(Optional.of(equipo));
        when(repository.save(any(Equipo.class))).thenReturn(equipo);

        Equipo resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdRetornaNull() {
        EquipoDTO dto = new EquipoDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Equipo resultado = service.updateById(dto, 99L);
        assertNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByJuegoPrincipalId() {
        when(repository.findByJuegoPrincipalId(1L)).thenReturn(Arrays.asList(new Equipo()));
        List<Equipo> resultado = service.findByJuegoPrincipalId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByCapitanId() {
        when(repository.findByCapitanId(2L)).thenReturn(Arrays.asList(new Equipo()));
        List<Equipo> resultado = service.findByCapitanId(2L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Equipo()));
        List<Equipo> resultado = service.findByEstado("ACTIVO");
        assertFalse(resultado.isEmpty());
    }
}