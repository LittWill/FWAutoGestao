package com.fwcorp.fwautogestao.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fwcorp.fwautogestao.services.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
	
	private final MarcaService marcaService;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@PostMapping(value = "/imagem", consumes = {"multipart/form-data"})
	public ResponseEntity<?> uploadImagemMarca(
			@RequestParam(name = "imagem") MultipartFile imagem)
			throws RuntimeException {

		
			URI uri = marcaService.uploadMarcaPicture(imagem,
					1L);

			return ResponseEntity.ok(uri.toString());

	}

}
