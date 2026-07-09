package com.Esport_manager.Game_service.Models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JuegoDTO {
    private Long id;

    @NotBlank(message = "El nombre del juego no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    @NotBlank(message = "La modalidad no puede estar vacía")
    private String modalidad;

    @NotNull(message = "El número de jugadores por equipo no puede ser nulo")
    @Min(value = 1, message = "Debe haber al menos 1 jugador por equipo")
    private Integer jugadoresPorEquipo;

    @NotBlank(message = "El email de soporte no puede estar vacío")
    @Email(message = "El formato del email es inválido")
    private String email;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}
