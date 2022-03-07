package com.fwcorp.fwautogestao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Gestor extends Usuario {
	
/*	@OneToMany(mappedBy = "quemGerou")
	private List <TokenRegistro> tokensGerados;
*/
	private static final long serialVersionUID = 1L;

	public Gestor () {
		this.setCargo(new Cargo("GESTOR"));
	}

}
