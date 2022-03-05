package com.fwcorp.fwautogestao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class TokenRegistro {
	
	@Id
	private Long numero;
	
	private boolean isTokenUtilizado;

}
