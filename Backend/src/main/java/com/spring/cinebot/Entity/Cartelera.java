package com.spring.cinebot.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="cartelera")
public class Cartelera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;

    @Column(name = "hora",nullable = false)
    private LocalTime hora;


    @ManyToOne
    @JoinColumn(name="Pelicula_id")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name="Cine_id")
    private Cine cine;

    public Cartelera(Integer id, LocalDate fecha, LocalTime hora, Pelicula pelicula, Cine cine) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pelicula = pelicula;
        this.cine = cine;
    }

    public Cartelera(LocalDate fecha, LocalTime hora, Pelicula pelicula, Cine cine) {
        this.fecha = fecha;
        this.hora = hora;
        this.pelicula = pelicula;
        this.cine = cine;
    }

    public Cartelera() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    @Override
    public String toString() {
        return "Cartelera{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", pelicula=" + pelicula +
                ", cine=" + cine +
                '}';
    }
}
