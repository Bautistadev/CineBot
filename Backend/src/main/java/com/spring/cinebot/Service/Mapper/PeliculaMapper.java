package com.spring.cinebot.Service.Mapper;


import com.spring.cinebot.DTO.GeneroDTO;
import com.spring.cinebot.DTO.GeneroRequestDTO;
import com.spring.cinebot.DTO.PeliculaDTO;
import com.spring.cinebot.DTO.PeliculaRequestDTO;
import com.spring.cinebot.Entity.Genero;
import com.spring.cinebot.Entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PeliculaMapper {

    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "director",target = "director")
    @Mapping(source = "duracion",target = "duracion")
    @Mapping(source = "generoId",target = "genero.id")
    public abstract Pelicula peliculaRequestDTOTopelicula(PeliculaRequestDTO generoRequestDTO);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "director",target = "director")
    @Mapping(source = "duracion",target = "duracion")
    @Mapping(source = "genero",target = "generoDTO")
    public abstract PeliculaDTO peliculaToPeliculaDTO(Pelicula pelicula);
}
