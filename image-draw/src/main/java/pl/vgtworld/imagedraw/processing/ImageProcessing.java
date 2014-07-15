package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import pl.vgtworld.imagedraw.ImageDrawEntity;

public class ImageProcessing {
	
	private ImageDrawEntity image;
	
	private ImageTypeMapper imageTypeMapper = new ImageTypeMapper();
	
	private ImageOpenActions openActions = new ImageOpenActions(imageTypeMapper);
	
	private ImageSaveActions saveActions = new ImageSaveActions(imageTypeMapper);
	
	private ImageResizeActions resizeActions = new ImageResizeActions();
	
	private ImageCropActions cropActions = new ImageCropActions();
	
	private ImageRotationActions rotationActions = new ImageRotationActions();
	
	private ImageThumbnailScaleActions thumbnailScaleActions = new ImageThumbnailScaleActions(resizeActions);
	
	private ImageThumbnailCropActions thumbnailCropActions = new ImageThumbnailCropActions(resizeActions, cropActions);
	
	public ImageDrawEntity getImage() {
		return image;
	}
	
	/**
	 * Opens image file from specified path for further processing.
	 * 
	 * @param path A path to read from.
	 * @throws IOException If an error occurs during reading.
	 */
	public void open(String path) throws IOException {
		image = openActions.open(new File(path));
	}
	
	/**
	 * Opens image file from specified file for further processing.
	 * 
	 * @param file A file to read from.
	 * @throws IOException If an error occurs during reading.
	 */
	public void open(File file) throws IOException {
		image = openActions.open(file);
	}
	
	/**
	 * Opens image file from specified stream for further processing.
	 * 
	 * @param stream A stream to read from.
	 * @throws IOException If an errors occurs during reading.
	 */
	public void open(InputStream stream) throws IOException {
		image = openActions.open(stream);
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
	
	public void crop(int x, int y, int width, int height) {
		cropActions.crop(image, x, y, width, height);
	}
	
	public void rotate(Rotation rotation) {
		rotationActions.rotate(image, rotation.getDegrees(), Color.BLACK);
	}
	
	public void rotate(int degrees, Color backgroundColor) {
		rotationActions.rotate(image, degrees, backgroundColor);
	}
	
	public void thumbnailScale(int width, int height) {
		thumbnailScaleActions.thumbnail(image, width, height, null);
	}
	
	public void thumbnailScale(int width, int height, Color backgroundColor) {
		thumbnailScaleActions.thumbnail(image, width, height, backgroundColor);
	}
	
	public void thumbnailCrop(int width, int height) {
		thumbnailCropActions.thumbnail(image, width, height);
	}
	
}
