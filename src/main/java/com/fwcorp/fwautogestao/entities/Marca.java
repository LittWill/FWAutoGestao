package com.fwcorp.fwautogestao.entities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fwcorp.fwautogestao.services.SecurityInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
@Entity
public class Marca {
	
	@Id
	private String id;
	
	@Column(length = 50, unique = true, nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String urlImagem;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataCriacao;
	
	@ManyToOne(optional = false)
	private Operador quemCriou;
	
	public Marca (String nome, String urlImagem) {
		this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.urlImagem = urlImagem;
		this.dataCriacao = LocalDateTime.now();
		this.quemCriou = (Operador) SecurityInfo.obterUsuarioLogado();
	}

	
}
