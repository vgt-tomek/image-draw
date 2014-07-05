package pl.vgtworld.imagedraw;

import java.awt.image.BufferedImage;

public class Image {
	
	private BufferedImage image;
	
	public Image() {
	}
	
	public Image(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
