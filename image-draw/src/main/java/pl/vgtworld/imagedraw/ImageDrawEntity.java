package pl.vgtworld.imagedraw;

import java.awt.image.BufferedImage;

/**
 * Entity class used as image container in various library classes.
 */
public class ImageDrawEntity {
	
	private BufferedImage image;
	
	private ImageType imageType;
	
	/**
	 * Default constructor creating empty object.
	 */
	public ImageDrawEntity() {
		// Default constructor creating empty object.
	}
	
	/**
	 * Custom constructor creating object with defined image and image type.
	 * 
	 * @param image Image data.
	 * @param imageType Image type.
	 */
	public ImageDrawEntity(BufferedImage image, ImageType imageType) {
		this.image = image;
		this.imageType = imageType;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public ImageType getImageType() {
		return imageType;
	}
	
	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}
	
}
