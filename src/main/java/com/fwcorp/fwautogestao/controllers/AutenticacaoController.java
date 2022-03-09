package com.fwcorp.fwautogestao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.dto.AutenticacaoDTO;
import com.fwcorp.fwautogestao.entities.RespostaServidor;
import com.fwcorp.fwautogestao.services.AutenticacaoService;
import com.fwcorp.fwautogestao.util.BindingResultUtils;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("auth")
@Api(tags = "Autenticação")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@ApiOperation(value = "Faz o login no sistema. Espera um email e uma senha", tags = "Acesso Livre")
	@PostMapping
	public ResponseEntity<RespostaServidor> autenticar(@Valid @RequestBody AutenticacaoDTO dto,
			BindingResult bindingResult) {
		
		List<String> errosDosCampos = BindingResultUtils.extrairErrosDosCampos(bindingResult);
		
		if (!errosDosCampos.isEmpty()) {
			return GeradorRespostaServidor.gerarRespostaServidorStatusBadRequest(errosDosCampos, "Não foi possível fazer o login!");
		}
		
		try {
			return GeradorRespostaServidor.gerarRespostaServidorStatusOk(autenticacaoService.autenticar(dto));
		} catch (AuthenticationException ae) {
			return GeradorRespostaServidor.gerarRespostaServidor(HttpStatus.UNAUTHORIZED, "Login ou senha inválidos!");
		}
	}

}
