package com.fwcorp.fwautogestao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fwcorp.fwautogestao.enums.Cargos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class TokenRegistro {
	
	@Id
	private Long numero;
	
	private boolean isTokenUtilizado;
	
	private Cargos cargo;

	public TokenRegistro(Cargos cargo) {
		this.numero = System.currentTimeMillis() / 100;
		this.isTokenUtilizado = false;
		this.cargo = cargo;
	}
	
	

}
