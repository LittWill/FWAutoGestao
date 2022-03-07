package com.fwcorp.fwautogestao;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fwcorp.fwautogestao.entities.Administrador;
import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.entities.Operador;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.repositories.CargoRepository;
import com.fwcorp.fwautogestao.repositories.TokenRegistroRepository;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class FwAutoGestaoApplication implements CommandLineRunner{
	
	private final CargoRepository cargoRepository;
	
	private final TokenRegistroRepository tokenRepository;
	
	private final UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(FwAutoGestaoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		cargoRepository.saveAll(List.of(new Cargo("ADMIN"), new Cargo("GESTOR"), new Cargo("OPERADOR")));
		
		/*TokenRegistro token2 = new TokenRegistro(Cargos.GESTOR);
		token1.setNumero(Long.valueOf(16000000));
		token2.setNumero(Long.valueOf(16000001));
		
		tokenRepository.save(token2);
		
		Usuario operador = token1.getCargo().getInstancia();
		operador.setId(token1.getNumero());
		operador.setPrimeiroNome("Wilson");
		operador.setUltimoNome("Rodrigues");
		operador.setEmail("wilsonfmz14@gmail.com");
		operador.setSenha(new BCryptPasswordEncoder().encode("1234"));
		operador.setUrlImagem("imagem.jpg");
		
		
		usuarioRepository.save(operador);
		token1.setTokenUtilizado(true);
		tokenRepository.save(token1);
		
		Usuario gestor = token2.getCargo().getInstancia();
		gestor.setId(token2.getNumero());
		gestor.setPrimeiroNome("Felipe");
		gestor.setUltimoNome("Rodrigues");
		gestor.setEmail("felipefmz14@gmail.com");
		gestor.setSenha(new BCryptPasswordEncoder().encode("1234"));
		gestor.setUrlImagem("imagem.jpg");
		
		
		usuarioRepository.save(gestor);
		token2.setTokenUtilizado(true);
		tokenRepository.save(token2);
		*/


	}

}
