package com.spring.cinebot.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="pelicula")

public class Pelicula {

    public Pelicula(Integer id, String nombre, String director, Integer duracion, Genero genero) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
    }

    public Pelicula() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="director",nullable = false)
    private String director;

    @Column(name="duracion",nullable = false)
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name="Genero_id")
    private Genero genero;

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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", duracion=" + duracion +
                ", genero=" + genero +
                '}';
    }
}
