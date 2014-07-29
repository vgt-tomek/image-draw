package pl.vgtworld.imagedraw.processing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageFlipActions {
	
	void flipHorizontal(ImageDrawEntity image) {
		BufferedImage bufferedImage = image.getImage();
		
		AffineTransform transform = new AffineTransform();
		transform.translate(bufferedImage.getWidth(), 0);
		transform.scale(-1, 1);
		
		BufferedImage flippedImage = flipImage(bufferedImage, transform);
		image.setImage(flippedImage);
	}
	
	void flipVertical(ImageDrawEntity image) {
		BufferedImage bufferedImage = image.getImage();
		
		AffineTransform transform = new AffineTransform();
		transform.translate(0, bufferedImage.getHeight());
		transform.scale(1, -1);
		
		BufferedImage flippedImage = flipImage(bufferedImage, transform);
		image.setImage(flippedImage);
	}
	
	private BufferedImage flipImage(BufferedImage bufferedImage, AffineTransform transform) {
		BufferedImage flippedImage = new BufferedImage(
				bufferedImage.getWidth(),
				bufferedImage.getHeight(),
				bufferedImage.getType()
				);
		Graphics2D graphics = flippedImage.createGraphics();
		graphics.drawImage(bufferedImage, transform, null);
		graphics.dispose();
		return flippedImage;
	}
	
}
