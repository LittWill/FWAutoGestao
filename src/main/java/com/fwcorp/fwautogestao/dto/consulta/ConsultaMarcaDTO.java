package com.fwcorp.fwautogestao.dto.consulta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMarcaDTO {
	
	private Long id;

	private String nome;
	
	private String urlImagem;

}
