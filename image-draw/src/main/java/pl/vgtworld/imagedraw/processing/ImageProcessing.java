package pl.vgtworld.imagedraw.processing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import pl.vgtworld.imagedraw.Image;
import pl.vgtworld.imagedraw.ImageType;

public class ImageProcessing {
	
	private ImageTypeMapper imageTypeMapper = new ImageTypeMapper();
	
	private ImageOpenActions openActions = new ImageOpenActions(imageTypeMapper);
	
	public Image open(String path) throws IOException {
		return openActions.open(new File(path));
	}
	
	public Image open(File file) throws IOException {
		return openActions.open(file);
	}
	
	public Image open(InputStream stream) throws IOException {
		return openActions.open(stream);
	}
	
	public Image open(InputStream stream, ImageType imageType) throws IOException {
		return openActions.open(stream, imageType);
	}
	
}
