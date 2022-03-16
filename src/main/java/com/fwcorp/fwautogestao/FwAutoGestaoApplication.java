package com.fwcorp.fwautogestao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fwcorp.fwautogestao.entities.Marca;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class FwAutoGestaoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	}

}
