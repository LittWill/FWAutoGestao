package com.fwcorp.fwautogestao.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.SecurityInfo;
import com.fwcorp.fwautogestao.services.UsuarioService;

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
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataGeracao;
	
	private boolean isTokenUtilizado;
	
	private Cargos cargo;
	
	@JsonIgnore
	@ManyToOne
	private Usuario quemGerou;

	public TokenRegistro(Cargos cargo) {
		this.numero = System.currentTimeMillis() / 100;
		this.isTokenUtilizado = false;
		this.dataGeracao = LocalDateTime.now();
		this.cargo = cargo;
		this.quemGerou = SecurityInfo.obterUsuarioLogado();
	}
	
	

}
