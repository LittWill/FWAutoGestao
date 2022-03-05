package com.fwcorp.fwautogestao.dto.registro;

import com.fwcorp.fwautogestao.enums.Cargos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class RegistroTokenDTO {
	
	private Cargos cargo;

	public void setCargo(String cargo) {
		this.cargo = Cargos.valueOf(cargo);
	}

}
