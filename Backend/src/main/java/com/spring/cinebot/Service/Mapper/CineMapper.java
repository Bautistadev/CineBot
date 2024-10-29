package com.spring.cinebot.Service.Mapper;

import com.spring.cinebot.DTO.CineDTO;
import com.spring.cinebot.DTO.CineRequestDTO;
import com.spring.cinebot.Entity.Cine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public  abstract class CineMapper {

    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "calle",target = "calle")
    @Mapping(source = "numero",target = "numero")
    public abstract Cine cineRequestDTOToCine(CineRequestDTO cineRequestDTO);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "calle",target = "calle")
    @Mapping(source = "numero",target = "numero")
    public abstract CineDTO cineToCineDTO(Cine cine);

}
