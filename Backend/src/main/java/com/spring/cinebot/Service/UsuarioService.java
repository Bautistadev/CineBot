package com.spring.cinebot.Service;

import com.spring.cinebot.DTO.UsuarioDTO;
import com.spring.cinebot.DTO.UsuarioRequestDTO;
import com.spring.cinebot.Exceptions.BadRequestException;
import com.spring.cinebot.Exceptions.ConflictException;
import com.spring.cinebot.Exceptions.NotContentException;
import com.spring.cinebot.Repository.UsuarioRepository;
import com.spring.cinebot.Service.Contract.UsuarioServiceContract;
import com.spring.cinebot.Service.Mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioServiceContract {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDTO> findAll() {

        if(this.usuarioRepository.findAll().isEmpty())
            throw new NotContentException("NO EXISTE CONTENIDO PERSISTIDO");

        return this.usuarioRepository.findAll().stream()
                .map(usuarioMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findById(Integer id) {
        if(id == null || id == 0)
            throw new BadRequestException("PETICION INVALIDA");

        return this.usuarioRepository.findById(id)
                .map(usuarioMapper::userToUserDTO)
                .orElseThrow(()->new NotContentException("USUARIO NO EXISTENTE"));
    }

    @Override
    public void save(UsuarioRequestDTO param) {

        if(param == null)
            throw new NotContentException("PARAMETRO VACIO");

        if(!this.validateEmail(param.getEmail()))
            throw new BadRequestException("MAIL INVALIDO");

        if(!this.validateText(param.getNombre()) || !this.validateText(param.getApellido()))
            throw new BadRequestException("NOMBRE O APELLIDO INVALIDO");

        if(!this.usuarioRepository.findByEmail(param.getEmail()).isEmpty())
            throw new ConflictException("EMAIL EXISTENTE");

        if(!this.usuarioRepository.findByTelefono(param.getTelefono()).isEmpty())
            throw new ConflictException("TELEFONO EXISTENTE");


        this.usuarioRepository.save(usuarioMapper.userRequestDTOToUser(param));

    }



    public UsuarioDTO findByEmail(String email){

        if(email == null || email.isEmpty())
            throw new NotContentException("PARAMETRO VACIO");

        if(!this.validateEmail(email))
            throw new BadRequestException("MAIL INVALIDO");

        return this.usuarioRepository
                .findByEmail(email)
                .map(usuarioMapper::userToUserDTO)
                .orElseThrow(()->new NotContentException("USUARIO NO EXISTENTE"));
    }


    public UsuarioDTO findByTelefono(String telefono){
        if(telefono == null)
            throw new NotContentException("PARAMETRO VACIO");

        if(allDigits(telefono))
            throw new BadRequestException("ERROR EN EL INGRESO EDL TELEFONO");

        return this.usuarioRepository
                .findByTelefono(telefono)
                .map(usuarioMapper::userToUserDTO)
                .orElseThrow(()-> new NotContentException("USUARIO NO EXISTENTE"));
    }



    @Override
    public Boolean existById(Integer id) {

        if(id == 0 || id == null)
            throw new BadRequestException("PETICION INVALIDA");

        if(!this.usuarioRepository.existsById(id))
            throw new ConflictException("USUARIO NO EXISTENTE");

        return this.usuarioRepository.existsById(id);
    }


    @Override
    public void delete(Integer genderId) {
        this.usuarioRepository.deleteById(genderId);
    }


    private boolean validateEmail(String validate){

        if(validate == null || validate == "") {
            return false;
        }
        // Verificar que contenga un "@" y un "." después del "@"
        int posicionArroba = validate.indexOf("@");
        int posicionPunto = validate.indexOf(".", posicionArroba);

        // Verificar que contenga ".com" en cualquier parte
        boolean contieneCom = validate.contains(".com");

        // El email es válido si contiene "@" no al inicio, un "." después del "@" y ".com" en cualquier lugar
        return posicionArroba > 0 && posicionPunto > posicionArroba && contieneCom;

    }

    private boolean validateText(String validate){

        if(validate == null || validate == "" || !validate.matches("[a-zA-Z]+"))
            return false;

        return true;
    }


    private Boolean allDigits(String input){
        if(input == null || input.isEmpty())
            return false;

        return input.chars().allMatch(Character::isDigit);
    }

}
