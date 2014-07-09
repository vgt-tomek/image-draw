package pl.vgtworld.imagedraw.processing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import pl.vgtworld.imagedraw.Image;
import pl.vgtworld.imagedraw.ImageType;

public class ImageProcessing {
	
	private Image image;
	
	private ImageTypeMapper imageTypeMapper = new ImageTypeMapper();
	
	private ImageOpenActions openActions = new ImageOpenActions(imageTypeMapper);
	
	private ImageSaveActions saveActions = new ImageSaveActions(imageTypeMapper);
	
	public Image getImage() {
		return image;
	}
	
	public void open(String path) throws IOException {
		image = openActions.open(new File(path));
	}
	
	public void open(File file) throws IOException {
		image = openActions.open(file);
	}
	
	public void open(InputStream stream) throws IOException {
		image = openActions.open(stream);
	}
	
	public Image open(InputStream stream, ImageType imageType) throws IOException {
		return openActions.open(stream, imageType);
	}
	
	public void save(String path) throws IOException {
		saveActions.save(image, new File(path));
	}
	
	public void save(File file) throws IOException {
		saveActions.save(image, file);
	}
	
}
