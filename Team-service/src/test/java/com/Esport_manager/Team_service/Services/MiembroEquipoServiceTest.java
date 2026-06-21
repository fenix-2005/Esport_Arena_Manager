package com.Esport_manager.Team_service.Services;

import com.Esport_manager.Team_service.Exceptions.MiembroNoEncontradoException;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Repositories.MiembroEquipoRepository;
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
public class MiembroEquipoServiceTest {

    @Mock
    private MiembroEquipoRepository repository;

    @InjectMocks
    private MiembroEquipoServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new MiembroEquipo()));
        List<MiembroEquipo> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        MiembroEquipo miembro = new MiembroEquipo();
        when(repository.findById(1L)).thenReturn(Optional.of(miembro));

        MiembroEquipo resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MiembroNoEncontradoException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        MiembroEquipoDTO dto = new MiembroEquipoDTO();
        MiembroEquipo miembro = new MiembroEquipo();

        when(repository.save(any(MiembroEquipo.class))).thenReturn(miembro);

        MiembroEquipo resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(MiembroEquipo.class));
    }

    @Test
    void updateByIdExitoso() {
        MiembroEquipoDTO dto = new MiembroEquipoDTO();
        MiembroEquipo miembro = new MiembroEquipo();

        when(repository.findById(1L)).thenReturn(Optional.of(miembro));
        when(repository.save(any(MiembroEquipo.class))).thenReturn(miembro);

        MiembroEquipo resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        MiembroEquipoDTO dto = new MiembroEquipoDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MiembroNoEncontradoException.class, () -> {
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
    void findByEquipoId() {
        when(repository.findByEquipoId(1L)).thenReturn(Arrays.asList(new MiembroEquipo()));
        List<MiembroEquipo> resultado = service.findByEquipoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void eliminarPorEquipoYUsuarioPresente() {
        MiembroEquipo miembro = new MiembroEquipo();
        when(repository.findByEquipoIdAndUsuarioId(1L, 2L)).thenReturn(Optional.of(miembro));
        doNothing().when(repository).delete(miembro);

        service.eliminarPorEquipoYUsuario(1L, 2L);

        verify(repository, times(1)).delete(miembro);
    }

    @Test
    void eliminarPorEquipoYUsuarioAusente() {
        when(repository.findByEquipoIdAndUsuarioId(1L, 2L)).thenReturn(Optional.empty());

        service.eliminarPorEquipoYUsuario(1L, 2L);

        // Verifica que no se llamó al método delete porque no existía
        verify(repository, never()).delete(any(MiembroEquipo.class));
    }
}
