package com.spring.cinebot.Repository;

import com.spring.cinebot.Entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Integer> {
}
