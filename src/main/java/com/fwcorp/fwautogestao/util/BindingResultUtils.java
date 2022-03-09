package com.fwcorp.fwautogestao.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultUtils {

	public static List<String> extrairErrosDosCampos(
			BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
	}

}
