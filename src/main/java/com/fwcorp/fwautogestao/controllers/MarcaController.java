package com.fwcorp.fwautogestao.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fwcorp.fwautogestao.config.MarcaMapper;
import com.fwcorp.fwautogestao.dto.consulta.ConsultaMarcaDTO;
import com.fwcorp.fwautogestao.dto.registro.RegistroMarcaDTO;
import com.fwcorp.fwautogestao.entities.Marca;
import com.fwcorp.fwautogestao.services.MarcaService;

@RestController
@RequestMapping("marcas")
public class MarcaController {
	
	private final MarcaService marcaService;
	
	private RegistroMarcaDTO registroMarcaDTOLocal;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@PostMapping
	public ResponseEntity<String> salvarMarca(@RequestBody RegistroMarcaDTO dto){
		this.registroMarcaDTOLocal = dto;
		return ResponseEntity.ok("Os dados foram armazenados!");
	}
	
	@GetMapping
	public ResponseEntity<List<ConsultaMarcaDTO>> listarMarcas(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(marcaService.listarMarcas(pageable).map(MarcaMapper::fromEntityToConsultaDTO).getContent());
	}

	@PostMapping(value = "/imagem")
	public ResponseEntity<Marca> uploadImagemMarca(
			@RequestParam(name = "imagem") MultipartFile imagem)
			throws RuntimeException {
		
			Marca marca = MarcaMapper.fromDtoToEntity(registroMarcaDTOLocal);
			Marca marcaSalva = marcaService.salvarMarca(marca);
		
			URI uri = marcaService.uploadMarcaPicture(imagem);
			
			marcaSalva.setUrlImagem(uri.toString());
			marcaService.salvarMarca(marcaSalva);

			return ResponseEntity.ok(marcaSalva);

	}

}
