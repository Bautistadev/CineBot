package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CineDTO {

    @NotBlank
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String calle;

    @NotBlank
    private Integer numero;

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

    public @NotNull String getCalle() {
        return calle;
    }

    public void setCalle(@NotNull String calle) {
        this.calle = calle;
    }

    public @NotBlank Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "CineDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                '}';
    }
}
