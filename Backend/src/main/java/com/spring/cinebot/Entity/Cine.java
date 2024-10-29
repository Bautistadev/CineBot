package com.spring.cinebot.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cine")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "calle",nullable = false)
    private String calle;

    @Column(name = "numero",nullable = false)
    private String numero;

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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
