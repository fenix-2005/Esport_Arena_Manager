package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(UsuarioDTO dto);
    Usuario updateById(UsuarioDTO dto, Long id);
    void deleteById(Long id);
    List<Usuario> findByRol(String rol);
    List<Usuario> findByEstado(String estado);
    Optional<Usuario> findByNickname(String nickname);
}
