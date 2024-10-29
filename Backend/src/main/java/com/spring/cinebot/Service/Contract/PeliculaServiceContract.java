package com.spring.cinebot.Service.Contract;


import com.spring.cinebot.DTO.PeliculaDTO;
import com.spring.cinebot.DTO.PeliculaRequestDTO;
import com.spring.cinebot.Entity.Pelicula;

import java.util.List;

public interface PeliculaServiceContract {
    public void save(PeliculaRequestDTO param);
    public void remove(Integer id);
    public List<PeliculaDTO> findAll();
    public PeliculaDTO findById(Integer id);
}
