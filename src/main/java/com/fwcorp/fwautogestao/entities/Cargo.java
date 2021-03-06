package com.fwcorp.fwautogestao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class Cargo implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "cargo")
	@ToString.Exclude
	private List<Usuario> usuarios;

	@Override
	public String getAuthority() {
		return this.nome;
	}

	public Cargo(String nome) {
		this.nome = nome;
	}

}