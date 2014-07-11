package pl.vgtworld.imagedraw;

import java.awt.image.BufferedImage;

public class ImageDrawEntity {
	
	private BufferedImage image;
	
	private ImageType imageType;
	
	public ImageDrawEntity() {
	}
	
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
