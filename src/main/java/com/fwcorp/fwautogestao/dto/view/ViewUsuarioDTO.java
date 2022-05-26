package com.fwcorp.fwautogestao.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ViewUsuarioDTO {

	private String id;

	private String primeiroNome;

	private String ultimoNome;
	
	private String urlImagem;
	
	private String email;

	private String cargo;

}
