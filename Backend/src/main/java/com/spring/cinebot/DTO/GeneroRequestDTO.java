package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneroRequestDTO {
    @NotNull
    private String nombre;

    public @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "GeneroRequestDTO{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
