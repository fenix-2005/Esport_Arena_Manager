package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Exceptions.PremioNoEncontradoException;
import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Repositories.PremioRepository;
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
public class PremioServiceTest {

    @Mock
    private PremioRepository repository;

    @InjectMocks
    private PremioServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Premio()));
        List<Premio> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Premio premio = new Premio();
        when(repository.findById(1L)).thenReturn(Optional.of(premio));

        Premio resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PremioNoEncontradoException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        PremioDTO dto = new PremioDTO();
        Premio premio = new Premio();

        when(repository.save(any(Premio.class))).thenReturn(premio);

        Premio resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Premio.class));
    }

    @Test
    void updateByIdExitoso() {
        PremioDTO dto = new PremioDTO();
        Premio premio = new Premio();

        when(repository.findById(1L)).thenReturn(Optional.of(premio));
        when(repository.save(any(Premio.class))).thenReturn(premio);

        Premio resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        PremioDTO dto = new PremioDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PremioNoEncontradoException.class, () -> {
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
        when(repository.findByTorneoId(1L)).thenReturn(Arrays.asList(new Premio()));
        List<Premio> resultado = service.findByTorneoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByPosicion() {
        when(repository.findByPosicion(1)).thenReturn(Arrays.asList(new Premio()));
        List<Premio> resultado = service.findByPosicion(1);
        assertFalse(resultado.isEmpty());
    }
}
