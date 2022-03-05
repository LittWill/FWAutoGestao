package com.fwcorp.fwautogestao.dto.registro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RegistroUsuarioDTO {
	
	private Long tokenRegistro;
	
	private String primeiroNome;

	private String ultimoNome;

	private String email;

	private String senha;

}
