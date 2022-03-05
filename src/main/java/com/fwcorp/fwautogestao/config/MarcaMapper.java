package com.fwcorp.fwautogestao.config;

import org.modelmapper.ModelMapper;

import com.fwcorp.fwautogestao.dto.consulta.ConsultaMarcaDTO;
import com.fwcorp.fwautogestao.dto.registro.RegistroMarcaDTO;
import com.fwcorp.fwautogestao.entities.Marca;

public class MarcaMapper {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static Marca fromDtoToEntity(RegistroMarcaDTO dto) {
		return modelMapper.map(dto, Marca.class);
	}
	
	public static ConsultaMarcaDTO fromEntityToConsultaDTO(Marca marca) {
		return modelMapper.map(marca, ConsultaMarcaDTO.class);
	}

}
