package com.EsportManager.Result_Service.Services;

import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Repositories.ResultadosRepository;
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
public class ResultadosServiceTest {

    @Mock
    private ResultadosRepository repository;

    @InjectMocks
    private ResultadosServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Resultados()));
        List<Resultados> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Resultados resultados = new Resultados();
        when(repository.findById(1L)).thenReturn(Optional.of(resultados));

        Resultados resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdRetornaNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Resultados resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        ResultadosDTO dto = new ResultadosDTO();
        Resultados resultados = new Resultados();

        when(repository.save(any(Resultados.class))).thenReturn(resultados);

        Resultados resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Resultados.class));
    }

    @Test
    void updateByIdExitoso() {
        ResultadosDTO dto = new ResultadosDTO();
        Resultados resultados = new Resultados();

        when(repository.findById(1L)).thenReturn(Optional.of(resultados));
        when(repository.save(any(Resultados.class))).thenReturn(resultados);

        Resultados resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdRetornaNull() {
        ResultadosDTO dto = new ResultadosDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Resultados resultado = service.updateById(dto, 99L);
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
        when(repository.findByTorneoId(1L)).thenReturn(Arrays.asList(new Resultados()));
        List<Resultados> resultado = service.findByTorneoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByPartidaId() {
        when(repository.findByPartidaId(2L)).thenReturn(Arrays.asList(new Resultados()));
        List<Resultados> resultado = service.findByPartidaId(2L);
        assertFalse(resultado.isEmpty());
    }
}
