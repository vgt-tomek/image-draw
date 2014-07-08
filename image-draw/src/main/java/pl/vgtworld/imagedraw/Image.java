package pl.vgtworld.imagedraw;

import java.awt.image.BufferedImage;

public class Image {
	
	private BufferedImage image;
	
	private ImageType imageType;
	
	public Image() {
	}
	
	public Image(BufferedImage image, ImageType imageType) {
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
