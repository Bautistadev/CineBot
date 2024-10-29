package com.spring.cinebot.Service.Mapper;

import com.spring.cinebot.DTO.GeneroDTO;
import com.spring.cinebot.DTO.GeneroRequestDTO;
import com.spring.cinebot.Entity.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GeneroMapper {

    @Mapping(source = "nombre",target = "nombre")
    public abstract Genero generoRequestDTOToGenero(GeneroRequestDTO param);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nombre",target = "nombre")
    public abstract GeneroDTO generoToGeneroDTO(Genero genero);
}
