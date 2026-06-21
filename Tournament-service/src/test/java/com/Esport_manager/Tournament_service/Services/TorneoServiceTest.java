package com.Esport_manager.Tournament_service.Services;

import com.Esport_manager.Tournament_service.Exceptions.TorneoNoEncontradoException;
import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;
import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Repositories.TorneoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TorneoServiceTest {

    @Mock
    private TorneoRepository repository;

    @InjectMocks
    private TorneoServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Torneo()));
        List<Torneo> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Torneo torneo = new Torneo();
        when(repository.findById(1L)).thenReturn(Optional.of(torneo));

        Torneo resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TorneoNoEncontradoException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        TorneoDTO dto = new TorneoDTO();
        Torneo torneo = new Torneo();

        when(repository.save(any(Torneo.class))).thenReturn(torneo);

        Torneo resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Torneo.class));
    }

    @Test
    void updateByIdExitoso() {
        TorneoDTO dto = new TorneoDTO();
        Torneo torneo = new Torneo();

        when(repository.findById(1L)).thenReturn(Optional.of(torneo));
        when(repository.save(any(Torneo.class))).thenReturn(torneo);

        Torneo resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        TorneoDTO dto = new TorneoDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TorneoNoEncontradoException.class, () -> {
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
    void findByJuegoId() {
        when(repository.findByJuegoId(1L)).thenReturn(Arrays.asList(new Torneo()));
        List<Torneo> resultado = service.findByJuegoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Torneo()));
        List<Torneo> resultado = service.findByEstado("ACTIVO");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByFechaInicio() {
        LocalDate fecha = LocalDate.now();
        when(repository.findByFechaInicio(fecha)).thenReturn(Arrays.asList(new Torneo()));
        List<Torneo> resultado = service.findByFechaInicio(fecha);
        assertFalse(resultado.isEmpty());
    }
}
