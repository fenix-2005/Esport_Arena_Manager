package Repositories;

import Models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository {


    List<Partida> findByTorneoId(String torneoId);

    List<Partida> findByEstado(String estado);

    List<Partida> findByRonda(int ronda);
}
