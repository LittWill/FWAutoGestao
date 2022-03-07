package com.fwcorp.fwautogestao.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fwcorp.fwautogestao.entities.Usuario;

public class SecurityInfo {
	
	public static Usuario obterUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
