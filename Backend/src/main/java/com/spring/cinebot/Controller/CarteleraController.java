package com.spring.cinebot.Controller;

import com.spring.cinebot.DTO.CarteleraDTO;
import com.spring.cinebot.Service.CarteleraService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/Cartelera/movies-by-gender")
    public ResponseEntity<List<CarteleraDTO>> getCarteleraByGender(@RequestParam("genderId") Integer genderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.carteleraService.findByGender(genderId));
    }

    @GetMapping("/Cartelera/movies-by-name")
    public ResponseEntity<List<CarteleraDTO>> getCarteleraByName(@RequestParam("name") String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.carteleraService.findByName(name));

    }
}