package com.fwcorp.fwautogestao.dto.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ViewUsuarioComumDTO {

	private String token;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataRegistro;

	private String primeiroNome;

	private String ultimoNome;

	private String urlImagem;

	private String email;

}
