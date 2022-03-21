package com.fwcorp.fwautogestao.mappers;

import org.modelmapper.ModelMapper;

import com.fwcorp.fwautogestao.dto.consulta.ConsultaTokenRegistroDTO;
import com.fwcorp.fwautogestao.dto.view.ViewTokenRegistroDTO;
import com.fwcorp.fwautogestao.entities.TokenRegistro;

public class TokenMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static ViewTokenRegistroDTO entityToViewTokenRegistroDTO(TokenRegistro tokenRegistro) {
		return modelMapper.map(tokenRegistro, ViewTokenRegistroDTO.class);
	}
	
	public static ConsultaTokenRegistroDTO entityToConsultaRegistroDTO(TokenRegistro tokenRegistro) {
		return modelMapper.map(tokenRegistro, ConsultaTokenRegistroDTO.class);
	}
}
