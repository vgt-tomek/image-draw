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
	
	/**
	 * Saves currently processed image to specified path.
	 * 
	 * @param path A path where image is saved.
	 * @throws IOException If an error occurs during writing.
	 */
	public void save(String path) throws IOException {
		saveActions.save(image, new File(path));
	}
	
	/**
	 * Saves currently processed image to specified file.
	 * 
	 * @param file A file where image is saved.
	 * @throws IOException If an error occurs during writing.
	 */
	public void save(File file) throws IOException {
		saveActions.save(image, file);
	}
	
	/**
	 * Resizes image to specified width and height.
	 * 
	 * <p>
	 * Both width and height cannot be equal or lower that zero.
	 * 
	 * <p>
	 * At least one of two parameters must be provided.
	 * 
	 * <p>
	 * If one of parameter's value is null, it's calculated automatically,
	 * therefore resized image has the same aspect ratio.
	 * 
	 * <p>
	 * If both parameters are provided, image is resized to exact
	 * dimensions, even if it will change image aspect ratio.
	 * 
	 * @param newWidth Image width after resizing.
	 * @param newHeight Image height after resizing.
	 */
	public void resize(Integer newWidth, Integer newHeight) {
		resizeActions.resize(image, newWidth, newHeight);
	}
	
	/**
	 * Cropping an area from image defined by specified rectangular region.
	 * 
	 * @param x X ccordinate of the upper-left corner of the specified region.
	 * @param y Y coordinate of the upper-left corner of the specified region.
	 * @param width Width of the specified region.
	 * @param height Height of the specified region.
	 */
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
