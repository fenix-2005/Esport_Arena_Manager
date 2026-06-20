package com.EsportManager.Match_Service.Services;

import com.EsportManager.Match_Service.Exceptions.PartidaNoEncontradaException;
import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Repositories.PartidaRepository;
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
public class PartidaServiceTest {

    @Mock
    private PartidaRepository repository;

    // Se inyecta la implementación exacta de tu servicio
    @InjectMocks
    private PartidaServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Partida()));
        List<Partida> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Partida partida = new Partida();
        when(repository.findById(1L)).thenReturn(Optional.of(partida));

        Partida resultado = service.findById(1L);

        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PartidaNoEncontradaException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        PartidaDTO dto = new PartidaDTO();
        dto.setEstado("PENDIENTE");
        Partida partida = new Partida();

        when(repository.save(any(Partida.class))).thenReturn(partida);

        Partida resultado = service.save(dto);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Partida.class));
    }

    @Test
    void updateByIdExitoso() {
        PartidaDTO dto = new PartidaDTO();
        dto.setEstado("FINALIZADO");
        Partida partida = new Partida();

        when(repository.findById(1L)).thenReturn(Optional.of(partida));
        when(repository.save(any(Partida.class))).thenReturn(partida);

        Partida resultado = service.updateById(dto, 1L);

        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        PartidaDTO dto = new PartidaDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PartidaNoEncontradaException.class, () -> {
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
    void findByTorneoId() {
        when(repository.findByTorneoId(1L)).thenReturn(Arrays.asList(new Partida()));
        List<Partida> resultado = service.findByTorneoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("PENDIENTE")).thenReturn(Arrays.asList(new Partida()));
        List<Partida> resultado = service.findByEstado("PENDIENTE");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByRonda() {
        when(repository.findByRonda(2)).thenReturn(Arrays.asList(new Partida()));
        List<Partida> resultado = service.findByRonda(2);
        assertFalse(resultado.isEmpty());
    }
}
