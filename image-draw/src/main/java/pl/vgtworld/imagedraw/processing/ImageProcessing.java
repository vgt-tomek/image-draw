package pl.vgtworld.imagedraw.processing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageProcessing {
	
	private ImageDrawEntity image;
	
	private ImageTypeMapper imageTypeMapper = new ImageTypeMapper();
	
	private ImageOpenActions openActions = new ImageOpenActions(imageTypeMapper);
	
	private ImageSaveActions saveActions = new ImageSaveActions(imageTypeMapper);
	
	private ImageResizeActions resizeActions = new ImageResizeActions();
	
	public ImageDrawEntity getImage() {
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
	
	public ImageDrawEntity open(InputStream stream, ImageType imageType) throws IOException {
		return openActions.open(stream, imageType);
	}
	
	public void save(String path) throws IOException {
		saveActions.save(image, new File(path));
	}
	
	public void save(File file) throws IOException {
		saveActions.save(image, file);
	}
	
	public void resize(Integer newWidth, Integer newHeight) {
		resizeActions.resize(image, newWidth, newHeight);
	}
	
}
