package com.spring.cinebot.Service.Contract;

import com.spring.cinebot.DTO.CarteleraRequestDTO;
import com.spring.cinebot.DTO.UsuarioDTO;

import com.spring.cinebot.DTO.UsuarioRequestDTO;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioServiceContract {

    public List<UsuarioDTO> findAll();
    public void save(UsuarioRequestDTO param);
    public void delete(Integer genderId);
    public UsuarioDTO findById(Integer id);
    public Boolean existById(Integer id);
}
