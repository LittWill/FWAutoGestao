package com.fwcorp.fwautogestao.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${s3.bucket}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3client;
	

	public URI uploadFile(MultipartFile multipartFile) {
		try {
		String fileName = multipartFile.getOriginalFilename();
		InputStream is = multipartFile.getInputStream();
		String contentType = multipartFile.getContentType();
		return uploadFile(is, fileName, contentType); 
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado");
			
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}

	}
	
	public void excluirImagem(String prefixo, Long objetoId) {
		String key = prefixo + objetoId + ".jpg";
		try {
			s3client.deleteObject(bucketName, key);
		}
		catch(SdkClientException ex) {
			ex.printStackTrace();
		}
		
	}

}
