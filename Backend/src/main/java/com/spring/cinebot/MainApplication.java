package com.spring.cinebot;


import com.spring.cinebot.DTO.PeliculaRequestDTO;
import com.spring.cinebot.Entity.Cartelera;
import com.spring.cinebot.Entity.Cine;
import com.spring.cinebot.Entity.Genero;
import com.spring.cinebot.Entity.Pelicula;
import com.spring.cinebot.Repository.*;


import com.spring.cinebot.Service.GeneroService;
import com.spring.cinebot.Service.Mapper.CarteleraMapper;
import com.spring.cinebot.Service.Mapper.GeneroMapper;
import com.spring.cinebot.Service.Mapper.PeliculaMapper;
import com.spring.cinebot.Service.Mapper.UsuarioMapper;
import com.spring.cinebot.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;


@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.cinebot"})
public class MainApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);

	}

	@Autowired
	ApplicationContext ctx;



	@Override
	public void run(String... args) throws Exception {

		UsuarioRepository repository = ctx.getBean(UsuarioRepository.class);
		UsuarioMapper usuarioMapper = ctx.getBean(UsuarioMapper.class);

		UsuarioService carteleraservice = ctx.getBean(UsuarioService.class);

		GeneroService generoRepository = ctx.getBean(GeneroService.class);
		GeneroMapper generoMapper = ctx.getBean(GeneroMapper.class);

		PeliculaRepository pe = ctx.getBean(PeliculaRepository.class);
		PeliculaMapper pem = ctx.getBean(PeliculaMapper.class);

		CarteleraRepository carteleraRepository = ctx.getBean(CarteleraRepository.class);
		CarteleraMapper carteleraMapper = ctx.getBean(CarteleraMapper.class);

		Pelicula pelicula = new Pelicula();
		pelicula.setId(1);

		Cine cine = new Cine();
		cine.setId(1);

		System.out.println(carteleraMapper.carteleraTOCarteleraDTO(carteleraRepository.findById(1).get()));


	}
}
