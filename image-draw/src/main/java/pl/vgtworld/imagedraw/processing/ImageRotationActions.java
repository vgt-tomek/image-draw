package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageRotationActions {
	
	void rotate(ImageDrawEntity image, int rotation, Color backgroundColor) {
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		Rectangle2D bounds = calculateBoundaryOfRotatedImage(width, height, rotation);
		AffineTransform transform = new AffineTransform();
		transform.translate(-bounds.getX(), -bounds.getY());
		transform.rotate(Math.toRadians(rotation));
		
		int newWidth = (int)bounds.getWidth();
		int newHeight = (int)bounds.getHeight();
		
		BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
		Graphics2D g = rotatedImage.createGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, newWidth, newHeight);
		g.drawImage(bufferedImage, transform, null);
		g.dispose();
		image.setImage(rotatedImage);
	}
	
	private Rectangle2D calculateBoundaryOfRotatedImage(int width, int height, int rotation) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotation));
		return transform.createTransformedShape(new Rectangle(width, height)).getBounds2D();
	}
	
}
