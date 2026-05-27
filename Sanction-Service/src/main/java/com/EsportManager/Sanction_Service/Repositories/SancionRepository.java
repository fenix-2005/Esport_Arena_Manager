package com.EsportManager.Sanction_Service.Repositories;

import com.EsportManager.Sanction_Service.Models.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SancionRepository extends JpaRepository<Sancion, Long> {

    List<Sancion> findByUsuarioId(Long usuarioId);

    List<Sancion> findByEquipoId(Long equipoId);

    List<Sancion> findByEstado(String estado);

    List<Sancion> findByUsuarioIdAndEstado(Long usuarioId, String estado);

    List<Sancion> findByEquipoIdAndEstado(Long equipoId, String estado);

    boolean existsByUsuarioIdAndEquipoIdAndEstado(Long usuarioId, Long equipoId, String estado);
}
