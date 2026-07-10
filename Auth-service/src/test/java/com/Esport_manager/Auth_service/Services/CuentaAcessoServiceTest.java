package com.Esport_manager.Auth_service.Services;

import com.Esport_manager.Auth_service.Clients.UsuarioClient;
import com.Esport_manager.Auth_service.Exceptions.CuentaNoEncontradaException;
import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Models.Dtos.CuentaAccesoDTO;
import com.Esport_manager.Auth_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
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

public class CuentaAcessoServiceTest {
    @Mock
    private CuentaAccesoRepository repository;

    @Mock private UsuarioClient usuarioClient;

    @InjectMocks
    private CuentaAccesoServiceLmpl service;

    @Test
    void guardarCuentaExitosamente() {
        // Prepara los datos que enviará el usuario
        CuentaAccesoDTO dto = new CuentaAccesoDTO();
        dto.setEmail("nuevo@esports.cl");
        dto.setPassword("12345");
        dto.setEstado("ACTIVO");
        dto.setRol("USER");
        dto.setFechaCreacion(LocalDate.now());

        // 2. CONFIGURACIÓN DEL MOCK
        when(usuarioClient.obtenerUsuarioPorEmail("nuevo@esports.cl")).thenReturn(new UsuarioDTO());

        // 3. CONFIGURACIÓN DEL REPOSITORIO
        when(repository.save(any(CuentaAcceso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 4. Ejecuta
        CuentaAcceso resultado = service.save(dto);

        // 5. Verifica
        assertNotNull(resultado);
        assertEquals("nuevo@esports.cl", resultado.getEmail());
        verify(usuarioClient, times(1)).obtenerUsuarioPorEmail("nuevo@esports.cl");
    }

    @Test
    void encontrarCuentaPorId() {
        // Define el ID a buscar y la cuenta que se encontrará
        Long idBuscado = 1L;
        CuentaAcceso cuentaMock = new CuentaAcceso();
        cuentaMock.setId(idBuscado);
        cuentaMock.setEmail("jugador@esports.cl");

        // Simula la búsqueda en la base de datos
        when(repository.findById(idBuscado)).thenReturn(Optional.of(cuentaMock));

        // Ejecuta la búsqueda en el servicio
        CuentaAcceso resultado = service.findById(idBuscado);

        // Verifica que se retorne la cuenta exacta solicitada
        assertNotNull(resultado);
        assertEquals(idBuscado, resultado.getId());
        assertEquals("jugador@esports.cl", resultado.getEmail());
    }

    @Test
    void lanzarExceptionCuandoIdNoExiste() {
        // Define un ID que no está registrado
        Long idInvalido = 99L;

        // Simula que la base de datos no encuentra ningún registro
        when(repository.findById(idInvalido)).thenReturn(Optional.empty());

        // Ejecuta la búsqueda y captura el error esperado
        CuentaNoEncontradaException excepcion = assertThrows(CuentaNoEncontradaException.class, () -> {
            service.findById(idInvalido);
        });

        // Valida que el mensaje de error sea el correcto para el usuario
        assertEquals("La cuenta con id 99 no existe", excepcion.getMessage());
        verify(repository, times(1)).findById(idInvalido);
    }

    @Test
    void listarTodasLasCuentas() {
        // Crea una lista de cuentas registradas
        CuentaAcceso c1 = new CuentaAcceso();
        c1.setId(1L);

        CuentaAcceso c2 = new CuentaAcceso();
        c2.setId(2L);

        List<CuentaAcceso> listaMock = Arrays.asList(c1, c2);

        // Simula la obtención de todos los registros
        when(repository.findAll()).thenReturn(listaMock);

        // Ejecuta la consulta general
        List<CuentaAcceso> resultado = service.findAll();

        // Verifica que la lista contenga todos los elementos creados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void eliminarCuentaPorId() {
        // Define el ID de la cuenta a dar de baja
        Long idAEliminar = 1L;

        // Ejecuta el método de eliminación
        service.deleteById(idAEliminar);

        // Confirma que la orden de borrado llegó efectivamente a la base de datos
        verify(repository, times(1)).deleteById(idAEliminar);
    }
    @Test
    void actualizarCuentaExitosamente() {
        // Prepara los datos simulados
        Long id = 1L;
        CuentaAccesoDTO dto = new CuentaAccesoDTO();
        dto.setEmail("update@esports.cl");
        dto.setPassword("newpass");
        dto.setRol("PLAYER");
        dto.setEstado("INACTIVO");
        dto.setFechaCreacion(LocalDate.now());

        CuentaAcceso cuentaExistente = new CuentaAcceso();
        cuentaExistente.setId(id);

        // Simula que la base de datos encuentra la cuenta y luego la guarda
        when(repository.findById(id)).thenReturn(Optional.of(cuentaExistente));
        when(repository.save(any(CuentaAcceso.class))).thenReturn(cuentaExistente);

        // Ejecuta la actualización
        CuentaAcceso resultado = service.updateById(dto, id);

        // Verifica el proceso
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(CuentaAcceso.class));
    }

    @Test
    void actualizarCuentaLanzaExcepcion() {
        Long idInvalido = 99L;
        CuentaAccesoDTO dto = new CuentaAccesoDTO();

        // Simula que no existe en la base de datos
        when(repository.findById(idInvalido)).thenReturn(Optional.empty());

        // Verifica que explote correctamente
        assertThrows(CuentaNoEncontradaException.class, () -> {
            service.updateById(dto, idInvalido);
        });
    }

    @Test
    void buscarPorRol() {
        String rol = "ADMIN";
        when(repository.findByRol(rol)).thenReturn(Arrays.asList(new CuentaAcceso()));

        List<CuentaAcceso> resultado = service.findByRol(rol);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorEstado() {
        String estado = "ACTIVO";
        when(repository.findByEstado(estado)).thenReturn(Arrays.asList(new CuentaAcceso()));

        List<CuentaAcceso> resultado = service.findByEstado(estado);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorEmailExitosamente() {
        String email = "test@esports.cl";
        CuentaAcceso cuenta = new CuentaAcceso();
        cuenta.setEmail(email);

        when(repository.findByEmail(email)).thenReturn(Optional.of(cuenta));

        CuentaAcceso resultado = service.findByEmail(email);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
    }

    @Test
    void buscarPorEmailLanzaExcepcion() {
        String email = "noexiste@esports.cl";
        when(repository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            service.findByEmail(email);
        });
    }
}
