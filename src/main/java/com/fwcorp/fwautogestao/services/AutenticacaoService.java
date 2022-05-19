package com.fwcorp.fwautogestao.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fwcorp.fwautogestao.dto.AutenticacaoDTO;
import com.fwcorp.fwautogestao.dto.TokenDTO;
import com.fwcorp.fwautogestao.dto.view.ViewTokenUsuarioDTO;
import com.fwcorp.fwautogestao.entities.Usuario;

@Service
public class AutenticacaoService {

	@Autowired
	private AuthenticationManager authManager;

	@Value("${com.fwcorp.fwautogestao.jwt.secret}")
	private String secret;

	@Value("${com.fwcorp.fwautogestao.jwt.expiration}")
	private String expiration;

	@Value("${com.fwcorp.fwautogestao.jwt.issuer}")
	private String issuer;

	public ViewTokenUsuarioDTO autenticar(AutenticacaoDTO authForm)
			throws AuthenticationException {
		Authentication authenticate = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authForm.getEmail(),
						authForm.getSenha()));

		String token = gerarToken(authenticate);

		return new ViewTokenUsuarioDTO((Usuario) authenticate.getPrincipal(),
				new TokenDTO(token).getToken());
	}

	private Algorithm criarAlgoritmo() {
		return Algorithm.HMAC256(secret);
	}

	private String gerarToken(Authentication authenticate) {
		Usuario principal = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(
				hoje.getTime() + Long.parseLong(expiration));

		return JWT.create().withIssuer(issuer).withExpiresAt(dataExpiracao)
				.withSubject(principal.getId().toString())
				.sign(this.criarAlgoritmo());

	}

	public boolean verificaToken(String token) {
		try {
			if (token == null) {
				return false;
			}

			JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build()
					.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}

	}

	public String retornarIdUsuario(String token) {
		String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer)
				.build().verify(token).getSubject();
		return subject;
	}
}
