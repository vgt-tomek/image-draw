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
	
	private static final float MAX_COLOR_CHANNEL_VALUE = 255f;

	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage sourceImage = image.getImage();
		RescaleOp op = new RescaleOp(-1f, MAX_COLOR_CHANNEL_VALUE, null);
		BufferedImage invertedImage = op.filter(sourceImage, null);
		
		Graphics2D graphics = sourceImage.createGraphics();
		graphics.drawImage(invertedImage, x, y, x + width, y + height, x, y, x + width, y + height, null);
		graphics.dispose();
	}
	
}
