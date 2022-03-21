package com.fwcorp.fwautogestao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fwcorp.fwautogestao.entities.Operador;
import com.fwcorp.fwautogestao.repositories.OperadorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OperadorService {
	
	private final OperadorRepository operadorRepository;
	
	public Page <Operador> listarOperadores(Pageable pageable){
		return this.operadorRepository.findAll(pageable);
	}

}
