package com.fwcorp.fwautogestao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario obterUsuario(TokenRegistro tokenRegistro) {
		return usuarioRepository.findById(tokenRegistro.getNumero()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}
	
	public Page <Usuario> listarUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	public void excluirUsuario(TokenRegistro tokenRegistro) {
		Usuario usuarioObtido = this.obterUsuario(tokenRegistro);
		usuarioRepository.delete(usuarioObtido);
	}

}
