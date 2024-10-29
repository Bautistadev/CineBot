package com.spring.cinebot.Service.Contract;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.DTO.CarteleraRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CarteleraServiceContract {

    public List<CarteleraDTO> findAll();
    public List<CarteleraDTO> findAllNext();
    public List<CarteleraDTO> findByGender(Integer genderId);
    public void save(CarteleraRequestDTO param);
    public void delete(Integer genderId);

}
