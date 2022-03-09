package com.fwcorp.fwautogestao.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fwcorp.fwautogestao.entities.RespostaServidor;

public class GeradorRespostaServidor {
	
	public static ResponseEntity<RespostaServidor> gerarRespostaServidorStatusOk(Object resposta) {
		HttpStatus status = HttpStatus.OK;
		return ResponseEntity.status(status).body(new RespostaServidor(status, resposta));
	}
	
	public static ResponseEntity<RespostaServidor> gerarRespostaServidorStatusCreated(Object resposta) {
		HttpStatus status = HttpStatus.CREATED;
		return ResponseEntity.status(status).body(new RespostaServidor(status, resposta));
	}
	
	public static ResponseEntity<RespostaServidor> gerarRespostaServidorStatusBadRequest(List <String> erros, Object resposta) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(new RespostaServidor(status, erros, resposta));
	}
	
	public static ResponseEntity<RespostaServidor> gerarRespostaServidor(HttpStatus status, List <String> erros, Object resposta) {
		return ResponseEntity.status(status).body(new RespostaServidor(status, erros, resposta));
	}
	
	public static ResponseEntity<RespostaServidor> gerarRespostaServidor(HttpStatus status, Object resposta) {
		return ResponseEntity.status(status).body(new RespostaServidor(status, resposta));
	}
	
	
	
	

}
