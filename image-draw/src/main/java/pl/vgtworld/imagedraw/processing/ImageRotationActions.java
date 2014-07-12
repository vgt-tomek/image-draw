package pl.vgtworld.imagedraw.processing;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageRotationActions {
	
	public void rotate(ImageDrawEntity image, int rotation) {
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		Rectangle bounds = calculateBoundaryOfRotatedImage(width, height, rotation);
		AffineTransform transform = new AffineTransform();
		transform.translate(-bounds.x, -bounds.y);
		transform.rotate(Math.toRadians(rotation));
		
		BufferedImage rotatedImage = new BufferedImage(bounds.width, bounds.height, bufferedImage.getType());
		Graphics2D g = rotatedImage.createGraphics();
		g.drawImage(bufferedImage, transform, null);
		g.dispose();
		image.setImage(rotatedImage);
	}
	
	private Rectangle calculateBoundaryOfRotatedImage(int width, int height, int rotation) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotation));
		return transform.createTransformedShape(new Rectangle(width, height)).getBounds();
	}
	
}
