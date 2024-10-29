package com.spring.cinebot.Service;

import com.spring.cinebot.DTO.PeliculaDTO;
import com.spring.cinebot.DTO.PeliculaRequestDTO;
import com.spring.cinebot.Exceptions.BadRequestException;
import com.spring.cinebot.Exceptions.NotContentException;
import com.spring.cinebot.Repository.PeliculaRepository;
import com.spring.cinebot.Service.Contract.PeliculaServiceContract;
import com.spring.cinebot.Service.Mapper.PeliculaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaService implements PeliculaServiceContract {

    private PeliculaRepository peliculaRepository;
    private PeliculaMapper peliculaMapper;

    public PeliculaService(PeliculaRepository peliculaRepository, PeliculaMapper peliculaMapper) {
        this.peliculaRepository = peliculaRepository;
        this.peliculaMapper = peliculaMapper;
    }

    @Override
    public void save(PeliculaRequestDTO param) {
        if(param == null)
            throw new BadRequestException("PARAMETRO VACIO");

        this.peliculaRepository.save(peliculaMapper.peliculaRequestDTOTopelicula(param));
    }

    @Override
    public void remove(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("ID NO VALIDO");

        if(!this.peliculaRepository.existsById(id))
            throw new NotContentException("NO EXISTE PELICULA");

        this.peliculaRepository.deleteById(id);
    }

    @Override
    public List<PeliculaDTO> findAll() {

        if (this.peliculaRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");

        return this.peliculaRepository.findAll()
                .stream()
                .map(peliculaMapper::peliculaToPeliculaDTO)
                .collect(Collectors.toList());

    }

    @Override
    public PeliculaDTO findById(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("PETICION INVALIDA");

        return this.peliculaRepository.findById(id)
                .map(peliculaMapper::peliculaToPeliculaDTO)
                .orElseThrow(()->new NotContentException("PELICULA NO EXISTENTE"));
    }
}
