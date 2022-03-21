package com.fwcorp.fwautogestao.dto.consulta;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ConsultaTokenRegistroDTO {
	
	private String token;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataGeracao;
	
	private boolean isTokenUtilizado;

}
