package com.fwcorp.fwautogestao.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fwcorp.fwautogestao.entities.RespostaServidor;
import com.fwcorp.fwautogestao.mappers.OperadorMapper;
import com.fwcorp.fwautogestao.services.OperadorService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("operadores")
public class OperadorController {
	
	private final OperadorService operadorService;
	
	@PreAuthorize("hasAuthority('GESTOR')")
	@GetMapping
	public ResponseEntity<RespostaServidor> obterOperadores(@PageableDefault Pageable pageable){
		return GeradorRespostaServidor.gerarRespostaServidorStatusOk(operadorService.listarOperadores(pageable).map(OperadorMapper::entityToViewOperadorDTO).getContent());
		
	}

}
