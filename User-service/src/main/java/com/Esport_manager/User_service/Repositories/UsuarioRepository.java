package com.Esport_manager.User_service.Repositories;

import com.Esport_manager.User_service.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNickname(String nickname);

    List<Usuario> findByRol(String rol);

    List<Usuario> findByEstado(String estado);
}
