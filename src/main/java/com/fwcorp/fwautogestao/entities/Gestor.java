package com.fwcorp.fwautogestao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
public class Gestor extends Usuario {

	@OneToMany(mappedBy = "quemGerou", fetch = FetchType.EAGER)
	private List<TokenRegistro> tokensGerados;

	private static final long serialVersionUID = 1L;

	public Gestor(TokenRegistro token, String primeiroNome, String ultimoNome, String urlImagem,
			String email, String senha) {
		super(token, primeiroNome, ultimoNome, urlImagem, email, senha);
		this.setCargo(new Cargo("GESTOR"));
	}
	
	
}
