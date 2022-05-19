package com.fwcorp.fwautogestao.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fwcorp.fwautogestao.services.SecurityInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class TokenRegistro {
	
	@Id
	private String token;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataGeracao;
	
	private boolean isUtilizado;
	
	@ManyToOne
	private Cargo tokenCargo;
	
	@JsonIgnore
	@ManyToOne
	private UsuarioGestor quemGerou;

	public TokenRegistro(Cargo cargo) {
		this.token = UUID.randomUUID().toString();
		this.isUtilizado = false;
		this.dataGeracao = LocalDateTime.now();
		this.tokenCargo = cargo;
		this.quemGerou = (UsuarioGestor) SecurityInfo.obterUsuarioLogado();
	}
	
	

}
