package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceLmpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario updateById(Usuario usuario, Long id) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            Usuario u = optional.get();
            u.setNombre(usuario.getNombre());
            u.setNickname(usuario.getNickname());
            u.setEmail(usuario.getEmail());
            u.setRol(usuario.getRol());
            u.setEstado(usuario.getEstado());
            u.setFechaRegistro(usuario.getFechaRegistro());
            return repository.save(u);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
