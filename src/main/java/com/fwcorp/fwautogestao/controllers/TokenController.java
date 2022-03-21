package com.fwcorp.fwautogestao.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.entities.Gestor;
import com.fwcorp.fwautogestao.entities.RespostaServidor;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.mappers.TokenMapper;
import com.fwcorp.fwautogestao.services.SecurityInfo;
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
		TokenRegistro tokenRegistro = tokenService
				.salvarToken(new TokenRegistro(new Cargo("GESTOR")));
		return GeradorRespostaServidor.gerarRespostaServidorStatusCreated(
				TokenMapper.entityToViewTokenRegistroDTO(tokenRegistro));
	}

	@PreAuthorize("hasAuthority('GESTOR')")
	@PostMapping("gerar/operador")
	public ResponseEntity<RespostaServidor> gerarTokenDeRegistroParaOperador() {
		TokenRegistro tokenRegistro = tokenService
				.salvarToken(new TokenRegistro(new Cargo("OPERADOR")));
		return GeradorRespostaServidor.gerarRespostaServidorStatusCreated(
				TokenMapper.entityToViewTokenRegistroDTO(tokenRegistro));

	}

	@GetMapping("verificar/{token}")
	public ResponseEntity<RespostaServidor> verificarToken(
			@PathVariable String token) {
		TokenRegistro tokenObtido = tokenService.buscarToken(token);
		if (tokenObtido == null) {
			return GeradorRespostaServidor.gerarRespostaServidor(
					HttpStatus.NOT_FOUND, "Token não encontrado!");
		}
		return GeradorRespostaServidor.gerarRespostaServidorStatusOk(
				tokenObtido.getCargo().getNome());
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR')")
	@GetMapping("meus")
	public ResponseEntity<RespostaServidor> obterMeusTokens() {
		Gestor usuarioLogado = (Gestor) SecurityInfo.obterUsuarioLogado();
		return GeradorRespostaServidor.gerarRespostaServidorStatusOk(
				usuarioLogado.getTokensGerados().stream()
						.map(TokenMapper::entityToConsultaRegistroDTO));
	}

	@PreAuthorize("hasAuthority('GESTOR')")
	@DeleteMapping("excluir/{token}")
	public ResponseEntity<RespostaServidor> excluirToken(
			@PathVariable String token) {
		TokenRegistro tokenObtido = tokenService.buscarToken(token);
		if (tokenObtido == null) {
			return GeradorRespostaServidor.gerarRespostaServidor(
					HttpStatus.NOT_FOUND, "Token não encontrado!");
		}

		tokenService.excluirToken(tokenObtido);

		return GeradorRespostaServidor
				.gerarRespostaServidorStatusOk("Token excluído!");
	}

}
