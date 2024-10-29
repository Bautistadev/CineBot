package com.spring.cinebot.Service.Contract;

import com.spring.cinebot.DTO.CineDTO;
import com.spring.cinebot.DTO.CineRequestDTO;

import java.util.List;

public interface CineServiceContract {

    public void save(CineRequestDTO param);
    public List<CineDTO> findAll();
    public CineDTO findById(Integer id);
    public void remove(Integer id);
}
