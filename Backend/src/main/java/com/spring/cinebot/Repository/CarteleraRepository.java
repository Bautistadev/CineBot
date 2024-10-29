package com.spring.cinebot.Repository;

import com.spring.cinebot.Entity.Cartelera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteleraRepository extends JpaRepository<Cartelera,Integer> {
}
