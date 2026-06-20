package com.Esport_manager.Game_service.Services;

import com.Esport_manager.Game_service.Models.Dtos.JuegoDTO;
import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Repositories.JuegoRepository;
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
public class JuegoServiceTest {

    @Mock
    private JuegoRepository repository;

    @InjectMocks
    private JuegoServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Juego()));
        List<Juego> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Juego juego = new Juego();
        when(repository.findById(1L)).thenReturn(Optional.of(juego));
        Juego resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdNoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Juego resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        JuegoDTO dto = new JuegoDTO();
        dto.setNombre("LoL");
        Juego juego = new Juego();

        when(repository.save(any(Juego.class))).thenReturn(juego);
        Juego resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Juego.class));
    }

    @Test
    void updateByIdExitoso() {
        JuegoDTO dto = new JuegoDTO();
        dto.setNombre("Valorant");
        Juego juego = new Juego();

        when(repository.findById(1L)).thenReturn(Optional.of(juego));
        when(repository.save(any(Juego.class))).thenReturn(juego);

        Juego resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdNoEncontrado() {
        JuegoDTO dto = new JuegoDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Juego resultado = service.updateById(dto, 99L);
        assertNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Juego()));
        List<Juego> resultado = service.findByEstado("ACTIVO");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByNombreExitoso() {
        Juego juego = new Juego();
        when(repository.findByNombre("LoL")).thenReturn(Optional.of(juego));
        Juego resultado = service.findByNombre("LoL");
        assertNotNull(resultado);
    }

    @Test
    void findByNombreNoEncontrado() {
        when(repository.findByNombre("Desconocido")).thenReturn(Optional.empty());
        Juego resultado = service.findByNombre("Desconocido");
        assertNull(resultado);
    }
}
