package com.fwcorp.fwautogestao.dto.registro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RegistroUsuarioDTO {

	@NotBlank(message = "É necessário incluir o token de registro!")
	private String tokenRegistro;

	@NotBlank(message = "É necessário incluir o primeiro nome!")
	private String primeiroNome;

	@NotBlank(message = "É necessário incluir o último nome!")
	private String ultimoNome;

	@NotBlank(message = "É necessário incluir a senha!")
	private String senha;

}
