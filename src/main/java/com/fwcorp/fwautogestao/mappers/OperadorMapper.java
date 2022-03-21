package com.fwcorp.fwautogestao.mappers;

import org.modelmapper.ModelMapper;

import com.fwcorp.fwautogestao.dto.view.ViewOperadorDTO;
import com.fwcorp.fwautogestao.entities.Operador;

public class OperadorMapper {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static ViewOperadorDTO entityToViewOperadorDTO(Operador operador) {
		return modelMapper.map(operador, ViewOperadorDTO.class);
	}

}
