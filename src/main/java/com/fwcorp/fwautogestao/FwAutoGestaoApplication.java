package com.fwcorp.fwautogestao;

import com.fwcorp.fwautogestao.entities.Administrador;
import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.repositories.CargoRepository;
import com.fwcorp.fwautogestao.repositories.TokenRegistroRepository;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

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

	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Administrador administrador = new Administrador("Wilson", "Almeida", "image.jpeg", "wilsonfmz14@gmail.com", "1234");
		System.out.println(administrador);
		usuarioRepository.save(administrador);

		cargoRepository.saveAll(List.of(new Cargo("GestorMaintainer"), new Cargo("Maintainer")));



		TokenRegistro tokenRegistro = tokenRegistroRepository.save(new TokenRegistro(administrador, new Cargo("Maintainer")));
		;

		Maintainer maintainer = new Maintainer(tokenRegistro, "Wiliam", "Almeida", "image.jpeg", "wilsonfmz14@gmail.com", "1234");
		usuarioRepository.save(maintainer);
	}

}
