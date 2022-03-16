package com.fwcorp.fwautogestao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.Gestor;
import com.fwcorp.fwautogestao.entities.Operador;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	private final TokenRegistroService tokenService;
	
	@PostMapping("gestor")
	public ResponseEntity<?> cadastrarGestor(
			@RequestBody RegistroUsuarioDTO dto) {

		TokenRegistro tokenBuscado = tokenService
				.buscarToken(dto.getTokenRegistro());

		
		Gestor gestor = new Gestor(tokenBuscado, dto.getPrimeiroNome(), dto.getUltimoNome(), "urlImagem.jpg", dto.getEmail(), dto.getSenha());
		
		usuarioService.salvarUsuario(gestor);
		tokenBuscado.setTokenUtilizado(true);
		tokenService.salvarToken(tokenBuscado);

		return ResponseEntity.ok("Usuário salvo!");
	}
	
	@PostMapping("operador")
	public ResponseEntity<?> cadastrarOperador(
			@RequestBody RegistroUsuarioDTO dto) {

		TokenRegistro tokenBuscado = tokenService
				.buscarToken(dto.getTokenRegistro());

		
		Operador operador = new Operador(tokenBuscado, dto.getPrimeiroNome(), dto.getUltimoNome(), "urlImagem.jpg", "wilsonfmz14@gmail.com", "1234");
		
		usuarioService.salvarUsuario(operador);
		tokenBuscado.setTokenUtilizado(true);
		tokenService.salvarToken(tokenBuscado);

		return ResponseEntity.ok("Usuário salvo!");
	}
}
