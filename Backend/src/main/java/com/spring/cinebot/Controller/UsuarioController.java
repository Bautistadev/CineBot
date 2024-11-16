package com.spring.cinebot.Controller;

import com.spring.cinebot.DTO.LoginDTO;
import com.spring.cinebot.DTO.ResponseTokenDTO;
import com.spring.cinebot.DTO.UsuarioDTO;
import com.spring.cinebot.DTO.UsuarioRequestDTO;
import com.spring.cinebot.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cineBot/")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @GetMapping("/Usuario")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findAll());
    }

    @GetMapping("/Usuario/findById")
    public ResponseEntity<UsuarioDTO> findById(@RequestParam("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findById(id));
    }

    @GetMapping("/Usuario/findByEmail")
    public ResponseEntity<UsuarioDTO> findByEmail(@RequestParam("email") String email){
        System.out.println("entra");
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findByEmail(email));
    }


    @PostMapping("/Usuario/save")
    public ResponseEntity save(@RequestBody UsuarioRequestDTO param){
        System.out.println(param);
        this.usuarioService.save(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/Login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.usuarioService.generateToken(loginDTO));

    }

}
