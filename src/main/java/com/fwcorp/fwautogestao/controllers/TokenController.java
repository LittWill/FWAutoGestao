package com.fwcorp.fwautogestao.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.entities.RespostaServidor;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("tokens")
@RestController
public class TokenController {
	
	private final TokenRegistroService tokenService;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("gerar/gestor")
	public ResponseEntity<?> gerarTokenDeRegistroParaGestor() {
		return ResponseEntity.ok(
				tokenService.salvarToken(new TokenRegistro(new Cargo("GESTOR"))));
	}

	@PreAuthorize("hasAuthority('GESTOR')")
	@PostMapping("gerar/operador")
	public ResponseEntity<?> gerarTokenDeRegistroParaOperador() {
		return ResponseEntity.ok(
				tokenService.salvarToken(new TokenRegistro(new Cargo("OPERADOR"))));
	}
	
	@GetMapping("verificar/{token}")
	public ResponseEntity<RespostaServidor> verificarToken(@PathVariable String token){
		TokenRegistro tokenObtido = tokenService.buscarToken(token);
		return GeradorRespostaServidor.gerarRespostaServidorStatusOk(tokenObtido.getCargo().getNome());
	}

}
