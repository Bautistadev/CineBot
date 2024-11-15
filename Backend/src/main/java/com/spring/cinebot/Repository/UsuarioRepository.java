package com.spring.cinebot.Repository;

import com.spring.cinebot.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Optional<Usuario> findByNombre(String nombre);
    public Optional<Usuario> findByEmail(String email);
    public Optional<Usuario> findByTelefono(String telefono);
}
