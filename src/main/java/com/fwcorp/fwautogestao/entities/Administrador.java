package com.fwcorp.fwautogestao.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fwcorp.fwautogestao.util.ExtratorDeCargo;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Administrador extends UsuarioGestor {

	private static final long serialVersionUID = 1L;

	public Administrador(String primeiroNome, String ultimoNome, String urlImagem, String email, String senha) {
		this.id = UUID.randomUUID().toString();
		this.cargo = ExtratorDeCargo.extrairCargo(this);
		this.dataRegistro = LocalDateTime.now();
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.urlImagem = urlImagem;
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

}
