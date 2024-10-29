package com.spring.cinebot.Controller;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.DTO.PeliculaDTO;
import com.spring.cinebot.DTO.PeliculaRequestDTO;
import com.spring.cinebot.Service.PeliculaService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cineBot/")
public class PeliculaController {

    private PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping("/Movies/allMovies")
    public ResponseEntity<List<PeliculaDTO>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(peliculaService.findAll());
    }

    @GetMapping("/Movies/MovieById/{id}")
    public ResponseEntity<PeliculaDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(peliculaService.findById(id));
    }

    @PostMapping("/Movie/save")
    public ResponseEntity save(@RequestBody PeliculaRequestDTO peliculaRequestDTO){
        this.peliculaService.save(peliculaRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/Movie/delete")
    public ResponseEntity remove(@PathVariable Integer id){
        this.peliculaService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
