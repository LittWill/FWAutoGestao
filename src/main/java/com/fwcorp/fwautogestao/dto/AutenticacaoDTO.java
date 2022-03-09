package com.fwcorp.fwautogestao.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AutenticacaoDTO {

	@NotBlank(message = "É necessário incluir o email!")
    private String email;

	@NotBlank(message = "É necessário incluir a senha!")
    private String senha;


}
