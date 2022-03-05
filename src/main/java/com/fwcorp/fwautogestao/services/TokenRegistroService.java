package com.fwcorp.fwautogestao.services;

import org.springframework.stereotype.Service;

import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.repositories.TokenRegistroRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenRegistroService {
	
	private final TokenRegistroRepository tokenRegistroRepository;
	
	public TokenRegistro salvarToken(TokenRegistro tokenRegistro) {
		return tokenRegistroRepository.save(tokenRegistro);
	}
	
	public TokenRegistro buscarToken(Long tokenRegistro) {
		return tokenRegistroRepository.findById(tokenRegistro).orElseThrow(() -> new RuntimeException("Token não encontrado!"));
	}


}
