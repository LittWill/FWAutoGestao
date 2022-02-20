package com.fwcorp.fwautogestao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
public class FwAutoGestaoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
