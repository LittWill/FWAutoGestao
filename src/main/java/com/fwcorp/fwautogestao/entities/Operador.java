package com.fwcorp.fwautogestao.entities;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Operador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	public Operador(TokenRegistro token, String primeiroNome, String ultimoNome, String urlImagem,
			String email, String senha) {
		super(token, primeiroNome, ultimoNome, urlImagem, email, senha);
		this.setCargo(new Cargo("OPERADOR"));
	}

}
