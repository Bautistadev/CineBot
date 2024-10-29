package com.spring.cinebot.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name="genero")

public class Genero {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Usuario> usuariosSuscritos;

    @JsonIgnore
    @OneToMany(mappedBy = "genero",fetch = FetchType.EAGER)
    private List<Pelicula> peliculaList;

    public Genero(Integer id, String nombre, List<Usuario> usuariosSuscritos, List<Pelicula> peliculaList) {
        this.id = id;
        this.nombre = nombre;
        this.usuariosSuscritos = usuariosSuscritos;
        this.peliculaList = peliculaList;
    }

    public Genero() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuariosSuscritos() {
        return usuariosSuscritos;
    }

    public void setUsuariosSuscritos(List<Usuario> usuariosSuscritos) {
        this.usuariosSuscritos = usuariosSuscritos;
    }

    public List<Pelicula> getPeliculaList() {
        return peliculaList;
    }

    public void setPeliculaList(List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return Objects.equals(id, genero.id) && Objects.equals(nombre, genero.nombre) && Objects.equals(peliculaList, genero.peliculaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, peliculaList);
    }
}
