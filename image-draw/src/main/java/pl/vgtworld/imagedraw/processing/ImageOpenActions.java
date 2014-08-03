package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

class ImageOpenActions {
	
	private static final ImageType DEFAULT_IMAGE_TYPE = ImageType.JPEG;
	
	private ImageTypeMapper imageTypeMapper;
	
	ImageOpenActions(ImageTypeMapper imageTypeMapper) {
		this.imageTypeMapper = imageTypeMapper;
	}
	
	ImageDrawEntity open(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		ImageType imageType = imageTypeMapper.mapFromExtension(file.getName());
		if (imageType == null) {
			imageType = DEFAULT_IMAGE_TYPE;
		}
		return new ImageDrawEntity(bufferedImage, imageType);
	}
	
	ImageDrawEntity open(InputStream stream) throws IOException {
		return open(stream, DEFAULT_IMAGE_TYPE);
	}
	
	ImageDrawEntity open(InputStream stream, ImageType imageType) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(stream);
		return new ImageDrawEntity(bufferedImage, imageType);
	}
	
}
