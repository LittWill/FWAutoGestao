package com.fwcorp.fwautogestao.dto;

import javax.validation.constraints.NotBlank;

public class AutenticacaoDTO {

	@NotBlank(message = "É necessário incluir o email!")
    private String email;

	@NotBlank(message = "É necessário incluir a senha!")
    private String senha;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

}
