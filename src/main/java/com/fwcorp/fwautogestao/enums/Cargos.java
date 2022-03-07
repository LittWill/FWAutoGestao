package com.fwcorp.fwautogestao.enums;

import com.fwcorp.fwautogestao.entities.Gestor;
import com.fwcorp.fwautogestao.entities.Operador;
import com.fwcorp.fwautogestao.entities.Usuario;

public enum Cargos {
	GESTOR(new Gestor()), OPERADOR(new Operador());

	Cargos(Usuario usuario) {
		this.instancia = usuario;
	}
	
	private Usuario instancia;

	public Usuario getInstancia() {
		return this.instancia;
	}

}
