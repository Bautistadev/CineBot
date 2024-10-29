package com.spring.cinebot.Service.Contract;

import com.spring.cinebot.DTO.GeneroDTO;
import com.spring.cinebot.DTO.GeneroRequestDTO;

import java.util.List;


public interface GeneroServiceContract {

    public void save(GeneroRequestDTO param);
    public void remove(Integer id);
    public List<GeneroDTO> findAll();
    public GeneroDTO findById(Integer id);
    public void genderSuscribe(Integer UserId,Integer GenederId);
}
