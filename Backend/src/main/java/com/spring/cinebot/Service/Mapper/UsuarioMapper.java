package com.spring.cinebot.Service.Mapper;

import com.spring.cinebot.DTO.UsuarioDTO;
import com.spring.cinebot.DTO.UsuarioRequestDTO;
import com.spring.cinebot.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "apellido",target = "apellido")
    @Mapping(source = "telefono",target = "telefono")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "password",target = "password")
    public abstract UsuarioDTO userToUserDTO(Usuario user);


    @Mapping(source = "nombre",target = "nombre")
    @Mapping(source = "apellido",target = "apellido")
    @Mapping(source = "telefono",target = "telefono")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "password",target = "password")
    public abstract Usuario userRequestDTOToUser(UsuarioRequestDTO userRequest);

}
