package com.fwcorp.fwautogestao.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.fwcorp.fwautogestao.util.ExtratorDeCargo;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
public abstract class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	protected String id;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	protected LocalDateTime dataRegistro;

	@Column(nullable = false)
	protected String primeiroNome;

	@Column(nullable = false)
	protected String ultimoNome;
	
	@Column(nullable = false)
	protected String urlImagem;
	
	@Column(nullable = false, unique = true)
	protected String email;
	
	@Column(nullable = false)
	protected String senha;
	
	@ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	protected Cargo cargo;

	protected Usuario(TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String email, String senha) {
		this.id = tokenRegistro.getToken();
		this.cargo = ExtratorDeCargo.extrairCargo(this);
		this.dataRegistro = LocalDateTime.now();
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.urlImagem = urlImagem;
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(this.cargo);
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
