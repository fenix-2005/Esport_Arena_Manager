package com.Esport_manager.User_service.Services;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.User_service.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceLmpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByRol(String rol) {
        return repository.findByRol(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }
}
