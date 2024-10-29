package com.spring.cinebot.Repository;

import com.spring.cinebot.Entity.Cine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CineRepository extends JpaRepository<Cine,Integer> {
}
