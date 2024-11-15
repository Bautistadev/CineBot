package com.spring.cinebot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
public class UsuarioDTO {

    @NotBlank
    private Integer id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String telefono;

    private List<GeneroDTO> generoDTOList;

    public UsuarioDTO(Integer id, String nombre, String apellido, String email, String password, String telefono, List<GeneroDTO> generoDTOList) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.generoDTOList = generoDTOList;
    }

    public UsuarioDTO() {
    }

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

    public @NotNull String getApellido() {
        return apellido;
    }

    public void setApellido(@NotNull String apellido) {
        this.apellido = apellido;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotNull String telefono) {
        this.telefono = telefono;
    }

    public List<GeneroDTO> getGeneroDTOList() {
        return generoDTOList;
    }

    public void setGeneroDTOList(List<GeneroDTO> generoDTOList) {
        this.generoDTOList = generoDTOList;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", generoDTOList=" + generoDTOList +
                '}';
    }
}
