package pl.vgtworld.imagedraw.filters.invertcolors;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

/**
 * Filter inverting colors on image.
 */
public class InvertColorsFilter implements ImageDrawFilter {
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage sourceImage = image.getImage();
		BufferedImage invertedImage = new BufferedImage(
				sourceImage.getWidth(),
				sourceImage.getHeight(),
				sourceImage.getType()
				);
		RescaleOp op = new RescaleOp(-1f, 255f, null);
		invertedImage = op.filter(sourceImage, null);
		
		Graphics2D graphics = sourceImage.createGraphics();
		graphics.drawImage(invertedImage, x, y, x + width, y + height, x, y, x + width, y + height, null);
		graphics.dispose();
	}
	
}
