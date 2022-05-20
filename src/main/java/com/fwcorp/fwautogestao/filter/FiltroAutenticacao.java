package com.fwcorp.fwautogestao.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.services.AutenticacaoService;
import com.fwcorp.fwautogestao.services.UsuarioService;


public class FiltroAutenticacao extends OncePerRequestFilter {

	private final AutenticacaoService autenticacaoService;

	private final UsuarioService usuarioService;

	public FiltroAutenticacao(AutenticacaoService autenticacaoService,
			UsuarioService usuarioService) {
		this.autenticacaoService = autenticacaoService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token = null;

		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
		}
		else if (header != null) {
			token = header;
		}

		System.out.println(token);

		if (autenticacaoService.verificaToken(token)) {
			String idUsuario = autenticacaoService.retornarIdUsuario(token);
			Usuario usuario = usuarioService.obterUsuario(idUsuario);
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(usuario, null,
							usuario.getAuthorities()));

		}

		filterChain.doFilter(request, response);

	}
}