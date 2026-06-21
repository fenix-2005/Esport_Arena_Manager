package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Repositories.UsuarioRepository;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Usuario()));
        List<Usuario> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Usuario usuario = new Usuario();
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdRetornaNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Usuario resultado = service.findById(99L);
        assertNull(resultado);
    }

    @Test
    void save() {
        UsuarioDTO dto = new UsuarioDTO();
        Usuario usuario = new Usuario();

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    void updateByIdExitoso() {
        UsuarioDTO dto = new UsuarioDTO();
        Usuario usuario = new Usuario();

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdRetornaNull() {
        UsuarioDTO dto = new UsuarioDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Usuario resultado = service.updateById(dto, 99L);
        assertNull(resultado);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByRol() {
        when(repository.findByRol("ADMIN")).thenReturn(Arrays.asList(new Usuario()));
        List<Usuario> resultado = service.findByRol("ADMIN");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByEstado() {
        when(repository.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Usuario()));
        List<Usuario> resultado = service.findByEstado("ACTIVO");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByNicknameExitoso() {
        Usuario usuario = new Usuario();
        when(repository.findByNickname("Player1")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = service.findByNickname("Player1");
        assertTrue(resultado.isPresent());
    }

    @Test
    void findByNicknameRetornaVacio() {
        when(repository.findByNickname("Ghost")).thenReturn(Optional.empty());

        Optional<Usuario> resultado = service.findByNickname("Ghost");
        assertFalse(resultado.isPresent());
    }
}
