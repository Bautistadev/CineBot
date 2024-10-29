package com.spring.cinebot.Controller;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.Service.CarteleraService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cineBot")
public class CarteleraController {

    private CarteleraService carteleraService;

    public CarteleraController(CarteleraService carteleraService) {
        this.carteleraService = carteleraService;
    }

    @GetMapping("/Cartelera/movies")
    public ResponseEntity<List<CarteleraDTO>> getCartelera() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.carteleraService.findAllNext());
    }

    @GetMapping("/Cartelera/movies-by-genre/")
    public ResponseEntity<List<CarteleraDTO>> getCarteleraByGender(@RequestParam("genreId") Integer genreId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.carteleraService.findByGender(genreId));
    }

    @GetMapping("/Cartelera/movies-by-name/")
    public ResponseEntity<List<CarteleraDTO>> getCarteleraByName(@RequestParam("movies_name") String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.carteleraService.findByName(name));

    }
}
