package com.spring.cinebot.DTO;

import com.spring.cinebot.Entity.Pelicula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarteleraDTO {

    @NotBlank
    private Integer id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime hora;

    @NotBlank
    private CineDTO cine ;

    @NotBlank
    private Pelicula pelicula;


    public @NotBlank Integer getId() {
        return id;
    }

    public void setId(@NotBlank Integer id) {
        this.id = id;
    }

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

    public @NotBlank CineDTO getCine() {
        return cine;
    }

    public void setCine(@NotBlank CineDTO cine) {
        this.cine = cine;
    }

    public @NotBlank Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(@NotBlank Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "CarteleraDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", cine=" + cine +
                ", pelicula=" + pelicula +
                '}';
    }
}
