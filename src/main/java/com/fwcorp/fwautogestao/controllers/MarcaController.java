package com.fwcorp.fwautogestao.controllers;

import java.net.URI;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("marcas")
@Api(tags = "Marcas Endpoints")
public class MarcaController {
	
	private final MarcaService marcaService;
	
	private RegistroMarcaDTO registroMarcaDTOLocal;

	@ApiOperation(value = "Sobe o formulário de uma nova marca", tags = "Maintainer")
	@PostMapping
	public ResponseEntity<String> salvarMarca(@RequestBody RegistroMarcaDTO dto){
		this.registroMarcaDTOLocal = dto;
		return ResponseEntity.ok("Os dados foram armazenados!");
	}

	@ApiOperation(value = "Listar todas as marcas cadastradas", tags = {"Maintainer", "Gestor de Maintainers", "Administrador"})
	@GetMapping
	public ResponseEntity<List<ConsultaMarcaDTO>> listarMarcas(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(marcaService.listarMarcas(pageable).map(MarcaMapper::fromEntityToConsultaDTO).getContent());
	}

	@ApiOperation(value = "Sobe a imagem da marca e finaliza o cadastro", tags = {"Maintainer"})
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
