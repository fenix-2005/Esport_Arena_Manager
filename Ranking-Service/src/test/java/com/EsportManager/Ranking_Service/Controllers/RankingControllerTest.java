package com.EsportManager.Ranking_Service.Controllers;

import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Services.RankingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RankingControllerTest {

    @Mock
    private RankingService rankingService;

    @InjectMocks
    private RankingController rankingController;

    @Test
    void crearRegistroRanking() {
        RankingDTO dto = new RankingDTO();
        Ranking ranking = new Ranking();
        when(rankingService.save(any(RankingDTO.class))).thenReturn(ranking);

        ResponseEntity<Ranking> response = rankingController.crearRegistroRanking(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(rankingService, times(1)).save(dto);
    }

    @Test
    void listarRankingSinTorneoId() {
        when(rankingService.findAll()).thenReturn(Arrays.asList(new Ranking()));

        ResponseEntity<List<Ranking>> response = rankingController.listarRanking(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rankingService, times(1)).findAll();
    }

    @Test
    void listarRankingConTorneoId() {
        when(rankingService.findByTorneoId(1L)).thenReturn(Arrays.asList(new Ranking()));

        ResponseEntity<List<Ranking>> response = rankingController.listarRanking(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rankingService, times(1)).findByTorneoId(1L);
    }

    @Test
    void buscarPosicionParticipanteEncontrado() {
        Ranking ranking = new Ranking();
        when(rankingService.findByTorneoIdAndParticipanteId(1L, 2L)).thenReturn(Optional.of(ranking));

        ResponseEntity<Ranking> response = rankingController.buscarPosicionParticipante(1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rankingService, times(1)).findByTorneoIdAndParticipanteId(1L, 2L);
    }

    @Test
    void buscarPosicionParticipanteNoEncontrado() {
        when(rankingService.findByTorneoIdAndParticipanteId(1L, 2L)).thenReturn(Optional.empty());

        ResponseEntity<Ranking> response = rankingController.buscarPosicionParticipante(1L, 2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(rankingService, times(1)).findByTorneoIdAndParticipanteId(1L, 2L);
    }

    @Test
    void buscarPorId() {
        Ranking ranking = new Ranking();
        when(rankingService.findById(1L)).thenReturn(ranking);

        ResponseEntity<Ranking> response = rankingController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rankingService, times(1)).findById(1L);
    }

    @Test
    void actualizarRanking() {
        RankingDTO dto = new RankingDTO();
        Ranking ranking = new Ranking();
        when(rankingService.updateById(any(RankingDTO.class), eq(1L))).thenReturn(ranking);

        ResponseEntity<Ranking> response = rankingController.actualizarRanking(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rankingService, times(1)).updateById(dto, 1L);
    }

    @Test
    void eliminarRegistro() {
        doNothing().when(rankingService).deleteById(1L);

        ResponseEntity<Void> response = rankingController.eliminarRegistro(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(rankingService, times(1)).deleteById(1L);
    }
}
