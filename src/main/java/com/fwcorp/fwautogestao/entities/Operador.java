package com.fwcorp.fwautogestao.entities;

import javax.persistence.Entity;

@Entity
public class Operador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	public Operador () {
		this.setCargo(new Cargo("OPERADOR"));
	}

}
