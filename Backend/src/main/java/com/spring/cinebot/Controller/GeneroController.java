package com.spring.cinebot.Controller;

import com.spring.cinebot.Service.GeneroService;
import com.spring.cinebot.DTO.GeneroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cineBot/")
public class GeneroController {

    private GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/Genero/findAll")
        public ResponseEntity<List<GeneroDTO>> suscribe(){
            return ResponseEntity.status(HttpStatus.OK).body(this.generoService.findAll());
        }
    
    @GetMapping("/Genero/suscribe")
    public ResponseEntity suscribe(@RequestParam("userId") Integer userId, @RequestParam("genderId") Integer genderId){
        this.generoService.genderSuscribe(userId,genderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
