package com.spring.cinebot.Service.Mapper;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.DTO.CarteleraRequestDTO;
import com.spring.cinebot.Entity.Cartelera;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CarteleraMapper {

    @Mapping(source = "fecha",target = "fecha")
    @Mapping(source = "hora",target = "hora")
    @Mapping(source = "peliculaId",target = "pelicula.id")
    @Mapping(source = "cineId",target = "cine.id")
    public abstract Cartelera carteleraRequestDTOCartelera(CarteleraRequestDTO carteleraRequestDTO);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "fecha",target = "fecha")
    @Mapping(source = "hora",target = "hora")
    @Mapping(source = "pelicula",target = "pelicula")
    @Mapping(source = "cine",target = "cine")
    public abstract CarteleraDTO carteleraTOCarteleraDTO(Cartelera cartelera);


}
