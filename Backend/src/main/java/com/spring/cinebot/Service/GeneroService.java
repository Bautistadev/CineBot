package com.spring.cinebot.Service;

import com.spring.cinebot.DTO.GeneroDTO;
import com.spring.cinebot.DTO.GeneroRequestDTO;
import com.spring.cinebot.Entity.Genero;
import com.spring.cinebot.Entity.Usuario;
import com.spring.cinebot.Exceptions.BadRequestException;
import com.spring.cinebot.Exceptions.ConflictException;
import com.spring.cinebot.Exceptions.NotContentException;
import com.spring.cinebot.Repository.GeneroRepository;
import com.spring.cinebot.Repository.UsuarioRepository;
import com.spring.cinebot.Service.Contract.GeneroServiceContract;
import com.spring.cinebot.Service.Mapper.GeneroMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService implements GeneroServiceContract {

    private GeneroRepository generoRepository;
    private GeneroMapper generoMapper;
    private UsuarioRepository usuarioRepository;

    public GeneroService(GeneroRepository generoRepository,UsuarioRepository usuarioRepository, GeneroMapper generoMapper) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void save(GeneroRequestDTO param) {

        if(param == null)
            throw new NotContentException("PARAMETRO VACIO");

        if(this.generoRepository.existsByNombre(param.getNombre()))
            throw new ConflictException("GENERO EXISTENTE");

        this.generoRepository.save(generoMapper.generoRequestDTOToGenero(param));

    }

    @Override
    public void remove(Integer id) {

        if(id == null || id == 0)
            throw new BadRequestException("ID NO VALIDO");

        if(!this.generoRepository.existsById(id))
            throw new ConflictException("GENERO NO EXISTENTE");

        this.generoRepository.deleteById(id);

    }

    @Override
    public List<GeneroDTO> findAll() {

        if(this.generoRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");

        return this.generoRepository.findAll()
                .stream()
                .map(generoMapper::generoToGeneroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GeneroDTO findById(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("PETICION INVALIDA");

        return this.generoRepository.findById(id)
                .map(generoMapper::generoToGeneroDTO)
                .orElseThrow(()->new NotContentException("USUARIO NO EXISTENTE"));
    }

    @Override
    @Transactional
    public void genderSuscribe(Integer UserId, Integer GenderId) {

        Usuario usuario = this.usuarioRepository.findById(UserId).get();
        Genero genero =  this.generoRepository.findById(GenderId).orElseThrow(()->new NotContentException("GENERO NO EXISTENTE"));

        usuario.suscribir(genero);

        this.usuarioRepository.save(usuario);
    }
}
