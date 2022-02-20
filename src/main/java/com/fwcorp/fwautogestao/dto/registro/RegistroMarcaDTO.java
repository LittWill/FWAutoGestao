package com.fwcorp.fwautogestao.dto.registro;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistroMarcaDTO {
	
	@NotBlank(message = "É necessário incluir o nome da marca!")
	private String nome;

}
