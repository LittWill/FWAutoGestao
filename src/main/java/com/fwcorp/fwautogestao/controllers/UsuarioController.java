package com.fwcorp.fwautogestao.controllers;

import org.joda.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.dto.registro.RegistroTokenDTO;
import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	private final TokenRegistroService tokenService;

	@PreAuthorize("hasAuthority('GESTOR')")
	@PostMapping
	public ResponseEntity<?> gerarTokenDeRegistro(
			@RequestBody RegistroTokenDTO dto) {
		return ResponseEntity.ok(
				tokenService.salvarToken(new TokenRegistro(dto.getCargo())));
	}

	@PostMapping("novo")
	public ResponseEntity<?> cadastrarUsuario(
			@RequestBody RegistroUsuarioDTO dto) {
		
		TokenRegistro tokenBuscado = tokenService
				.buscarToken(dto.getTokenRegistro());
		
		var usuario = tokenBuscado.getCargo().getInstancia();
		
		usuario.setId(tokenBuscado.getNumero());
		usuario.setPrimeiroNome(dto.getPrimeiroNome());
		usuario.setUltimoNome(dto.getUltimoNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(new BCryptPasswordEncoder().encode(dto.getSenha()));
		usuario.setUrlImagem("imagem.jpg");
		
		
		usuarioService.salvarUsuario(usuario);
		tokenBuscado.setTokenUtilizado(true);
		tokenService.salvarToken(tokenBuscado);
		
		return ResponseEntity.ok("Usuário salvo!");
	}
}
