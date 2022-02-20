package com.fwcorp.fwautogestao.services;

import java.awt.image.BufferedImage;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fwcorp.fwautogestao.entities.Marca;
import com.fwcorp.fwautogestao.repositories.MarcaRepository;

@Service
public class MarcaService {

	private final MarcaRepository marcaRepository;
	
	private final ImageService imageService;
	
	private final S3Service s3Service;

	@Value("${img.prefix.marca.profile}")
	private String imagePrefix;

	@Value("${img.profile.marca.size}")
	private Integer imageLogoSize;

	public MarcaService(MarcaRepository marcaRepository,
			ImageService imageService, S3Service s3Service) {
		this.marcaRepository = marcaRepository;
		this.imageService = imageService;
		this.s3Service = s3Service;
	}

	public Marca salvarMarca(Marca marca) {
		return marcaRepository.save(marca);
	}
	
	public URI uploadMarcaPicture(MultipartFile multipartFile, Long marcaId) {

		BufferedImage jpgImage = imageService
				.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.resize(jpgImage, imageLogoSize);
		String fileName = imagePrefix + marcaId + ".jpg";
		URI uri = s3Service.uploadFile(
				imageService.getInputStream(jpgImage, "jpg"), fileName,
				"image");

		return uri;

	}

	public Marca obterMarca(Long marcaId) {
		return marcaRepository.findById(marcaId).orElseThrow(
				() -> new RuntimeException("A marca não foi encontrada!"));
	}
	
	public Page<Marca> listarMarcas(Pageable pageable){
		return marcaRepository.findAll(pageable);
	}
	
	public Marca atualizarMarca(Marca marcaAtualizada, Long marcaOriginalId) {
		marcaAtualizada.setId(this.obterMarca(marcaOriginalId).getId());
		return this.salvarMarca(marcaAtualizada);
	}
	
	public void excluirMarca(Long marcaId) {
		marcaRepository.delete(this.obterMarca(marcaId));
	}

}
