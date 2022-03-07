package com.fwcorp.fwautogestao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.TokenRegistroService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("tokens")
@RestController
public class TokenController {
	
	private final TokenRegistroService tokenService;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("gerar/gestor")
	public ResponseEntity<?> gerarTokenDeRegistroParaGestor() {
		Cargos gestor = Cargos.GESTOR;
		return ResponseEntity.ok(
				tokenService.salvarToken(new TokenRegistro(gestor)));
	}

	@PreAuthorize("hasAuthority('GESTOR')")
	@PostMapping("gerar/operador")
	public ResponseEntity<?> gerarTokenDeRegistroParaOperador() {
		Cargos operador = Cargos.OPERADOR;
		return ResponseEntity.ok(
				tokenService.salvarToken(new TokenRegistro(operador)));
	}

}
