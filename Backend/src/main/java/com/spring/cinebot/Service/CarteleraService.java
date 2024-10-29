package com.spring.cinebot.Service;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.DTO.CarteleraRequestDTO;
import com.spring.cinebot.Exceptions.BadRequestException;
import com.spring.cinebot.Exceptions.NotContentException;
import com.spring.cinebot.Repository.CarteleraRepository;
import com.spring.cinebot.Service.Contract.CarteleraServiceContract;
import com.spring.cinebot.Service.Mapper.CarteleraMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteleraService implements CarteleraServiceContract {


    private CarteleraRepository carteleraRepository;
    private CarteleraMapper carteleraMapper;

    public CarteleraService(CarteleraRepository carteleraRepository,CarteleraMapper carteleraMapper){
        this.carteleraRepository = carteleraRepository;
        this.carteleraMapper = carteleraMapper;
    }


    @Override
    public List<CarteleraDTO> findAll() {

        if(carteleraRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");


        return this.carteleraRepository.findAll()
                .stream()
                .map(carteleraMapper::carteleraTOCarteleraDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteleraDTO> findAllNext() {
        if(carteleraRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");

        return this.carteleraRepository.findAll()
                .stream()
                .filter(o -> o.getFecha().isAfter(LocalDate.now()))
                .map(carteleraMapper::carteleraTOCarteleraDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteleraDTO> findByGender(Integer genderId) {

        if(genderId == null || genderId == 0)
            throw new BadRequestException("PETICION INVALIDA");

        return this.carteleraRepository.findAll()
                .stream()
                .filter(o -> o.getPelicula().getGenero().getId() == genderId)
                .map(carteleraMapper::carteleraTOCarteleraDTO)
                .collect(Collectors.toList());
    }

    public List<CarteleraDTO> findByName(String name) {

        if(name == null)
            throw new BadRequestException("PETICION INVALIDA");

        return this.carteleraRepository.findAll()
                .stream()
                .filter(o -> o.getPelicula().getGenero().getNombre().equalsIgnoreCase(name))
                .map(carteleraMapper::carteleraTOCarteleraDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(CarteleraRequestDTO param) {
        if(param == null)
            throw new BadRequestException("PARAMETRO VACIO");

        this.carteleraRepository.save(carteleraMapper.carteleraRequestDTOCartelera(param));

    }



    @Override
    public void delete(Integer genderId) {
        if(genderId == null || genderId == 0)
            throw new BadRequestException("PETICION INVALIDA");

        this.carteleraRepository.deleteById(genderId);
    }
}
