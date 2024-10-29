package com.spring.cinebot.Controller;

import com.spring.cinebot.Service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cineBot/")
public class GeneroController {

    private GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/Genero/suscribe/{userId}/{genderId}")
    public ResponseEntity suscribe(@PathVariable("userId") Integer userId, @PathVariable("genderId") Integer genderId){
        this.generoService.genderSuscribe(userId,genderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
