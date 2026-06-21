package com.Esport_manager.User_service.Controllers;

import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void crearUsuario() {
        UsuarioDTO dto = new UsuarioDTO();
        Usuario usuario = new Usuario();
        when(usuarioService.save(any(UsuarioDTO.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.crearUsuario(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(usuarioService, times(1)).save(dto);
    }

    @Test
    void listarUsuariosPorRol() {
        when(usuarioService.findByRol("ADMIN")).thenReturn(Arrays.asList(new Usuario()));
        ResponseEntity<List<Usuario>> response = usuarioController.listarUsuarios("ADMIN", null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioService, times(1)).findByRol("ADMIN");
    }

    @Test
    void listarUsuariosPorEstado() {
        when(usuarioService.findByEstado("ACTIVO")).thenReturn(Arrays.asList(new Usuario()));
        ResponseEntity<List<Usuario>> response = usuarioController.listarUsuarios(null, "ACTIVO");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioService, times(1)).findByEstado("ACTIVO");
    }

    @Test
    void listarUsuariosSinFiltros() {
        when(usuarioService.findAll()).thenReturn(Arrays.asList(new Usuario()));
        ResponseEntity<List<Usuario>> response = usuarioController.listarUsuarios(null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioService, times(1)).findAll();
    }

    @Test
    void buscarPorIdEncontrado() {
        Usuario usuario = new Usuario();
        when(usuarioService.findById(1L)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioService, times(1)).findById(1L);
    }

    @Test
    void buscarPorIdNoEncontrado() {
        when(usuarioService.findById(99L)).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.buscarPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void buscarPorNicknameEncontrado() {
        Usuario usuario = new Usuario();
        when(usuarioService.findByNickname("Player1")).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.buscarPorNickname("Player1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorNicknameNoEncontrado() {
        when(usuarioService.findByNickname("Ghost")).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioController.buscarPorNickname("Ghost");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void actualizarUsuarioEncontrado() {
        UsuarioDTO dto = new UsuarioDTO();
        Usuario usuario = new Usuario();
        when(usuarioService.updateById(any(UsuarioDTO.class), eq(1L))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioService, times(1)).updateById(dto, 1L);
    }

    @Test
    void actualizarUsuarioNoEncontrado() {
        UsuarioDTO dto = new UsuarioDTO();
        when(usuarioService.updateById(any(UsuarioDTO.class), eq(99L))).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(99L, dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void desactivarUsuario() {
        doNothing().when(usuarioService).deleteById(1L);

        ResponseEntity<Void> response = usuarioController.desactivarUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).deleteById(1L);
    }
}
