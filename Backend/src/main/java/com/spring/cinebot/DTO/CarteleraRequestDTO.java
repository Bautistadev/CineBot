package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarteleraRequestDTO {

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime hora;

    @NotBlank
    private Integer peliculaId;

    @NotBlank
    private Integer cineId;


    public @NotNull LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull LocalDate fecha) {
        this.fecha = fecha;
    }

    public @NotNull LocalTime getHora() {
        return hora;
    }

    public void setHora(@NotNull LocalTime hora) {
        this.hora = hora;
    }

    public @NotBlank Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(@NotBlank Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public @NotBlank Integer getCineId() {
        return cineId;
    }

    public void setCineId(@NotBlank Integer cineId) {
        this.cineId = cineId;
    }

    @Override
    public String toString() {
        return "CarteleraRequestDTO{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", peliculaId=" + peliculaId +
                ", cineId=" + cineId +
                '}';
    }
}
