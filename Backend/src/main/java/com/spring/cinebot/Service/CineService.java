package com.spring.cinebot.Service;

import com.spring.cinebot.DTO.CineDTO;
import com.spring.cinebot.DTO.CineRequestDTO;
import com.spring.cinebot.Exceptions.BadRequestException;
import com.spring.cinebot.Exceptions.NotContentException;
import com.spring.cinebot.Repository.CineRepository;
import com.spring.cinebot.Service.Contract.CineServiceContract;
import com.spring.cinebot.Service.Mapper.CineMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CineService implements CineServiceContract {

    private CineRepository cineRepository;
    private CineMapper cineMapper;

    public CineService(CineRepository cineRepository, CineMapper cineMapper) {
        this.cineRepository = cineRepository;
        this.cineMapper = cineMapper;
    }

    @Override
    public void save(CineRequestDTO param) {
        if(param == null)
            throw new BadRequestException("PARAMETRO VACIO");

        this.cineRepository.save(cineMapper.cineRequestDTOToCine(param));
    }

    @Override
    public List<CineDTO> findAll() {
        if(this.cineRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");


        return this.cineRepository.findAll()
                .stream()
                .map(cineMapper::cineToCineDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CineDTO findById(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("PETICION INVALIDA");

        return this.cineRepository.findById(id)
                .map(cineMapper::cineToCineDTO)
                .orElseThrow(()->new NotContentException("PELICULA NO EXISTENTE"));

    }

    @Override
    public void remove(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("ID NO VALIDO");

        if(!this.cineRepository.existsById(id))
            throw new NotContentException("NO EXISTE CINE");

        this.cineRepository.deleteById(id);

    }
}
