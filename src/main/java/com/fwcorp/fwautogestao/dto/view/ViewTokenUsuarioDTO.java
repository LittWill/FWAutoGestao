package com.fwcorp.fwautogestao.dto.view;

import com.fwcorp.fwautogestao.entities.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTokenUsuarioDTO {

	private ViewUsuarioDTO usuario;
	private String token;

	public ViewTokenUsuarioDTO(Usuario usuario, String token) {
		this.usuario = new ViewUsuarioDTO(
				usuario.getPrimeiroNome(), usuario.getUltimoNome(),
				usuario.getUrlImagem(), usuario.getEmail(),
				usuario.getCargo().getNome());
		this.token = token;
	}

}
