package com.fwcorp.fwautogestao.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String extensao = FilenameUtils
				.getExtension(uploadedFile.getOriginalFilename());

		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(extensao)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler arquivo!");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(),
				img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException ex) {
			throw new RuntimeException("Erro ao ler arquivo!");
		}
	}

	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
}
