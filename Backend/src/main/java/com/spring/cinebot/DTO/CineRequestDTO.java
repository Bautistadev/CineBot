package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CineRequestDTO {

    @NotNull
    private String nombre;

    @NotNull
    private String calle;

    @NotBlank
    private Integer numero;

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
        return "CineRequestDTO{" +
                "nombre='" + nombre + '\'' +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                '}';
    }
}
