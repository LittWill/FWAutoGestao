package com.fwcorp.fwautogestao.entities;

import javax.persistence.Entity;


@Entity
public class Gestor extends Usuario {

	private static final long serialVersionUID = 1L;

	public Gestor () {
		this.setCargo(new Cargo("GESTOR"));
	}

}
