package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

class ImageSaveActions {
	
	private ImageTypeMapper imageTypeMapper;
	
	public ImageSaveActions(ImageTypeMapper imageTypeMapper) {
		this.imageTypeMapper = imageTypeMapper;
	}
	
	public void save(ImageDrawEntity image, File file) throws IOException {
		BufferedImage imageData = image.getImage();
		ImageType imageType = image.getImageType();
		if (imageData == null) {
			throw new IllegalStateException("Undefined image data.");
		}
		if (imageType == null) {
			throw new IllegalStateException("Undefined image type.");
		}
		String extension = imageTypeMapper.mapFromImageType(imageType);
		ImageIO.write(imageData, extension, file);
	}
	
}
