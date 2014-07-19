package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
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
	 * Saves currently processed image to specified path.
	 * 
	 * <p>
	 * Image type is only used during save process.
	 * After image is saved, its type is reverted back to what it was before.
	 * 
	 * @param path A path where image is saved.
	 * @param imageType Image format of output file.
	 * @throws IOException If an error occurs during writing.
	 */
	public void save(String path, ImageType imageType) throws IOException {
		ImageType currentImageType = image.getImageType();
		image.setImageType(imageType);
		saveActions.save(image, new File(path));
		image.setImageType(currentImageType);
	}
	
	/**
	 * Saves currently processed image to specified file.
	 * 
	 * <p>
	 * Image type is only used during save process.
	 * After image is saved, its type is reverted back to what it was before.
	 * 
	 * @param file A file where image is saved.
	 * @param imageType Image format of output file.
	 * @throws IOException If an error occurs during writing.
	 */
	public void save(File file, ImageType imageType) throws IOException {
		ImageType currentImageType = image.getImageType();
		image.setImageType(imageType);
		saveActions.save(image, file);
		image.setImageType(currentImageType);
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
	
	/**
	 * Rotates image by multiplicity of 90 degrees.
	 * 
	 * <p>
	 * Rotation parameter allows to choose one of three rotation types:
	 * <li>90 degrees clockwise
	 * <li>90 degrees counterclockwise
	 * <li>180 degrees
	 * 
	 * @param rotation Rotation type.
	 */
	public void rotate(Rotation rotation) {
		rotationActions.rotate(image, rotation.getDegrees(), Color.BLACK);
	}
	
	/**
	 * Rotates image by custom angle.
	 * 
	 * @param degrees Amount of degrees to rotate clockwise.
	 * @param backgroundColor Color, that should be used to fill area around rotated image.
	 */
	public void rotate(int degrees, Color backgroundColor) {
		rotationActions.rotate(image, degrees, backgroundColor);
	}
	
	/**
	 * Creates image thumbnail.
	 * 
	 * <p>
	 * If image is bigger, than defined width and height, it will be scaled down to fit defined size.
	 *
	 * <p>
	 * If image is smaller, than defined width and height, no action is taken.
	 * 
	 * <p>
	 * Scaled thumbnail have the same image aspect ratio as original image, therefore if original image has
	 * different aspect ratio, than thumbnail it will not use all available width or height.
	 * If it is important to create thumbnail with exact defined width and height, use
	 * {@link #thumbnailScale(int, int, Color) thumbnailScale(width, height, backgroundColor)}
	 * or {@link #thumbnailCrop(int, int) thumbnailCrop(width, height)}.
	 * 
	 * @param width Maximum allowed thumbnail width.
	 * @param height Maximum allowed thumbnail height.
	 */
	public void thumbnailScale(int width, int height) {
		thumbnailScaleActions.thumbnail(image, width, height, null);
	}
	
	/**
	 * Creates image thumbnail.
	 * 
	 * <p>
	 * if image is bigger, than defined width and height, it will be scaled down to fit defined size.
	 * 
	 * <p>
	 * If image is smaller, than defined width and height, it will be centered on thumbnail
	 * and surrounding area will be filled with background color.
	 * 
	 * <p>
	 * Scaled thumbnail have the same image aspect ratio as original image, therefore if original image has
	 * different aspect ratio, then thumbnail will have horizontal or vertical bars filled with background color,
	 * in order for generated image to have exact defined width and height.
	 * 
	 * @param width Thumbnail width.
	 * @param height Thumbnail height.
	 * @param backgroundColor Background color used to fill area around image.
	 */
	public void thumbnailScale(int width, int height, Color backgroundColor) {
		thumbnailScaleActions.thumbnail(image, width, height, backgroundColor);
	}
	
	/**
	 * Creates image thumbnail.
	 * 
	 * <p>
	 * If image is bigger, than defined width and height, it will be scaled down to fit defined size.
	 * 
	 * <p>
	 * If image is smaller, than defined width and height, it will be scaled up to fit defined size.
	 * 
	 * <p>
	 * Sacled thumbnail have the same image aspect ratio as original image, therefore if original image has
	 * different aspect ratio, then top/bottom or left/right edges (depending on aspect ratio difference)
	 * will be cut off in order for generated image to have exact defined width and height.
	 * If it is important to have whole image on thumbnail, use
	 * {@link #thumbnailScale(int, int, Color) thumbnailScale(width, height, backgroundColor)}
	 * or {@link #thumbnailScale(int, int) thumbnailScale(width, height)}.
	 * 
	 * @param width Thumbnail width.
	 * @param height Thumbnail height.
	 */
	public void thumbnailCrop(int width, int height) {
		thumbnailCropActions.thumbnail(image, width, height);
	}
	
}
