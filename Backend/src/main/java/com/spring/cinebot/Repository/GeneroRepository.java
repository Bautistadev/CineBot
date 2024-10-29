package com.spring.cinebot.Repository;

import com.spring.cinebot.Entity.Cartelera;
import com.spring.cinebot.Entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Integer> {

    public Boolean existsByNombre(String nombre);

}
