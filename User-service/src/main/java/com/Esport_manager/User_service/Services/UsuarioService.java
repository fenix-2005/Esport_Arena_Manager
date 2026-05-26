package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    Usuario updateById(Usuario usuario, Long id);
    void deleteById(Long id);
}
