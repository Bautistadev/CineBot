package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaRequestDTO {

    @NotNull
    private String nombre;

    @NotNull
    private String director;

    @NotBlank
    private Integer duracion;

    @NotNull
    private Integer generoId;

    public PeliculaRequestDTO(String nombre, String director, Integer duracion, Integer generoId) {
        this.nombre = nombre;
        this.director = director;
        this.duracion = duracion;
        this.generoId = generoId;
    }

    public PeliculaRequestDTO() {
    }

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public @NotNull String getDirector() {
        return director;
    }

    public void setDirector(@NotNull String director) {
        this.director = director;
    }

    public @NotBlank Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(@NotBlank Integer duracion) {
        this.duracion = duracion;
    }

    public @NotNull Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(@NotNull Integer generoId) {
        this.generoId = generoId;
    }

    @Override
    public String toString() {
        return "PeliculaRequestDTO{" +
                "nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", duracion=" + duracion +
                ", generoId=" + generoId +
                '}';
    }
}

