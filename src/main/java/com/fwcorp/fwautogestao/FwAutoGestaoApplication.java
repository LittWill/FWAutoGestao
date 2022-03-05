package com.fwcorp.fwautogestao;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.repositories.CargoRepository;

@SpringBootApplication
public class FwAutoGestaoApplication implements CommandLineRunner{
	
	private final CargoRepository cargoRepository;

	public FwAutoGestaoApplication(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		cargoRepository.saveAll(List.of(new Cargo("GESTOR"), new Cargo("OPERADOR")));
	}

}
