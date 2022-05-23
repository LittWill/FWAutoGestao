package com.fwcorp.fwautogestao;

import com.fwcorp.fwautogestao.entities.Administrador;
import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.repositories.CargoRepository;
import com.fwcorp.fwautogestao.repositories.TokenRegistroRepository;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;
import com.fwcorp.fwautogestao.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class FwAutoGestaoApplication implements CommandLineRunner{

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TokenRegistroRepository tokenRegistroRepository;

	@Autowired
	CargoRepository cargoRepository;

	@Autowired
	UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//usuarioService.gerarEmail("Felipe", "Almeida");
	}

}
