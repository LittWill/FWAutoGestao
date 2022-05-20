package com.fwcorp.fwautogestao.services;

import java.util.Optional;

import com.fwcorp.fwautogestao.enums.Cargos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public TokenRegistro buscarToken(String token) {
		Optional<TokenRegistro> opt_tokenRegistro = tokenRegistroRepository
				.findById(token);
		if (opt_tokenRegistro.isPresent()) {
			TokenRegistro tokenRegistro = opt_tokenRegistro.get();
			if (!tokenRegistro.isUtilizado()) {
				return tokenRegistro;
			}
		}
		
		return null;

	}

	public void excluirToken(TokenRegistro tokenRegistro) {
		tokenRegistroRepository.delete(tokenRegistro);
	}

	public Page<TokenRegistro> listarTokensGestorMaintainer(Pageable pageable){
		return tokenRegistroRepository.findByTokenCargoNome(pageable, Cargos.GESTORMAINTAINER.getNomeCargo());
	}

	public Page<TokenRegistro> listarTokensMaintainers(Pageable pageable){
		return tokenRegistroRepository.findByTokenCargoNome(pageable, Cargos.MAINTAINER.getNomeCargo());
	}
}
