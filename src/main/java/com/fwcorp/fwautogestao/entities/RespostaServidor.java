package com.fwcorp.fwautogestao.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespostaServidor {
	
	private List <String> erros = new ArrayList<>();
	
	private Integer statusCode;
	
	private Object resposta;
	
	public RespostaServidor (HttpStatus status, List <String> erros, Object resposta) {
		this.statusCode = status.value();
		this.erros = erros;
		this.resposta = resposta;
	}

	public RespostaServidor(HttpStatus status, Object resposta) {
		this.statusCode = status.value();
		this.resposta = resposta;
	}
	
	
	

}
