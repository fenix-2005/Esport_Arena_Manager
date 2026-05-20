package com.Esport_manager.Game_service.Repositories;


import com.Esport_manager.Game_service.Models.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface JuegoRepository extends JpaRepository {

    //Busca por nombre y manda nada si esta vacio
    Optional<Juego> findByNombre(String nombre);

    //Lista todos los juegos por Estado
    List <Juego> findByEstado(String estado);

}
