package com.fwcorp.fwautogestao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class Usuario implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;

	@OneToOne
	@Id
	private TokenRegistro token;
	
	@Column(nullable = false)
	private String primeiroNome;

	@Column(nullable = false)
	private String ultimoNome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@ManyToOne
	private Cargo cargo;

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
