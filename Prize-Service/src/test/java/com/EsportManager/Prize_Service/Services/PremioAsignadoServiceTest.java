package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Repositories.PremioAsignadoRepository;
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
public class PremioAsignadoServiceTest {

    @Mock
    private PremioAsignadoRepository repository;

    @InjectMocks
    private PremioAsignadoServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new PremioAsignado()));
        List<PremioAsignado> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        PremioAsignado asignado = new PremioAsignado();
        when(repository.findById(1L)).thenReturn(Optional.of(asignado));

        PremioAsignado resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdRetornaNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        PremioAsignado resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        PremioAsignadoDTO dto = new PremioAsignadoDTO();
        PremioAsignado asignado = new PremioAsignado();

        when(repository.save(any(PremioAsignado.class))).thenReturn(asignado);

        PremioAsignado resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(PremioAsignado.class));
    }

    @Test
    void updateByIdExitoso() {
        PremioAsignadoDTO dto = new PremioAsignadoDTO();
        PremioAsignado asignado = new PremioAsignado();

        when(repository.findById(1L)).thenReturn(Optional.of(asignado));
        when(repository.save(any(PremioAsignado.class))).thenReturn(asignado);

        PremioAsignado resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdRetornaNull() {
        PremioAsignadoDTO dto = new PremioAsignadoDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        PremioAsignado resultado = service.updateById(dto, 99L);
        assertNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByParticipanteId() {
        when(repository.findByParticipanteId(1L)).thenReturn(Arrays.asList(new PremioAsignado()));
        List<PremioAsignado> resultado = service.findByParticipanteId(1L);
        assertFalse(resultado.isEmpty());
    }
}
