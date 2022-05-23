package com.fwcorp.fwautogestao.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Administrador extends UsuarioGestor {

	private static final long serialVersionUID = 1L;

	public Administrador (String primeiroNome, String ultimoNome, String urlImagem, String senha){
		super(primeiroNome, ultimoNome, urlImagem, senha);
	}



}
