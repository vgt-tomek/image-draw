package pl.vgtworld.imagedraw.processing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageDrawActions {
	
	void drawImage(ImageDrawEntity source, ImageDrawEntity destination, int x, int y) {
		BufferedImage sourceImage = source.getImage();
		BufferedImage destinationImage = destination.getImage();
		
		Graphics2D graphics = destinationImage.createGraphics();
		graphics.drawImage(sourceImage, x, y, null);
		graphics.dispose();
	}
	
}
