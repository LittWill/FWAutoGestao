package com.fwcorp.fwautogestao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario obterUsuario(Long token) {
		return usuarioRepository.findById(token).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}
	
	public Page <Usuario> listarUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	public void excluirUsuario(Long token) {
		Usuario usuarioObtido = this.obterUsuario(token);
		usuarioRepository.delete(usuarioObtido);
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}
	

}
