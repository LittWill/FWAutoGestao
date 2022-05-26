package com.fwcorp.fwautogestao.mappers;

import com.fwcorp.fwautogestao.dto.view.ViewUsuarioComumDTO;
import com.fwcorp.fwautogestao.entities.UsuarioComum;
import org.modelmapper.ModelMapper;

public class UsuarioMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ViewUsuarioComumDTO entityToViewUsuarioComumDTO(UsuarioComum usuarioComum) {
        return modelMapper.map(usuarioComum, ViewUsuarioComumDTO.class);
    }
	
/*	public static ViewUsuarioComumDTO entityToViewUsuarioComumDTO(UsuarioGestorComum usuarioGestorComum) {
		return modelMapper.map(usuarioGestorComum, ViewUsuarioComumDTO.class);
	}

 */
}
