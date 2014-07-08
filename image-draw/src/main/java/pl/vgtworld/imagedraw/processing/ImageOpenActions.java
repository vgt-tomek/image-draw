package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import pl.vgtworld.imagedraw.Image;
import pl.vgtworld.imagedraw.ImageType;

class ImageOpenActions {
	
	private static final ImageType DEFAULT_IMAGE_TYPE = ImageType.JPEG;
	
	private ImageTypeMapper imageTypeMapper;
	
	public ImageOpenActions(ImageTypeMapper imageTypeMapper) {
		this.imageTypeMapper = imageTypeMapper;
	}
	
	public Image open(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		ImageType imageType = imageTypeMapper.mapFromExtension(file.getName());
		if (imageType == null) {
			imageType = DEFAULT_IMAGE_TYPE;
		}
		return new Image(bufferedImage, imageType);
	}
	
	public Image open(InputStream stream) throws IOException {
		return open(stream, DEFAULT_IMAGE_TYPE);
	}
	
	public Image open(InputStream stream, ImageType imageType) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(stream);
		return new Image(bufferedImage, imageType);
	}
	
}
