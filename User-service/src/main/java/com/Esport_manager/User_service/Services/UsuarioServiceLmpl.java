package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
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
    public Usuario save(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setNickname(dto.getNickname());
        u.setEmail(dto.getEmail());
        u.setRol(dto.getRol());
        u.setEstado(dto.getEstado());
        u.setFechaRegistro(dto.getFechaRegistro());
        return repository.save(u);
    }

    @Override
    public Usuario updateById(UsuarioDTO dto, Long id) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            Usuario u = optional.get();
            u.setNombre(dto.getNombre());
            u.setNickname(dto.getNickname());
            u.setEmail(dto.getEmail());
            u.setRol(dto.getRol());
            u.setEstado(dto.getEstado());
            u.setFechaRegistro(dto.getFechaRegistro());
            return repository.save(u);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        return repository.findByRol(rol);
    }

    @Override
    public List<Usuario> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public Optional<Usuario> findByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }
}
