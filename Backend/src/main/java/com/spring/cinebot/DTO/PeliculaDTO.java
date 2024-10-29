package com.spring.cinebot.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {

    @NotBlank
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String director;

    @NotBlank
    private Integer duracion;

    private GeneroDTO generoDTO;

    public @NotBlank Integer getId() {
        return id;
    }

    public void setId(@NotBlank Integer id) {
        this.id = id;
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

    public GeneroDTO getGeneroDTO() {
        return generoDTO;
    }

    public void setGeneroDTO(GeneroDTO generoDTO) {
        this.generoDTO = generoDTO;
    }

    @Override
    public String toString() {
        return "PeliculaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", duracion=" + duracion +
                ", generoDTO=" + generoDTO +
                '}';
    }
}
