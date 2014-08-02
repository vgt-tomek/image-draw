package pl.vgtworld.imagedraw.filters.mosaic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public class MosaicFilter implements ImageDrawFilter {
	
	private int size;
	
	public MosaicFilter(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0.");
		}
		this.size = size;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage imageData = image.getImage();
		int scaledWidth = imageData.getWidth() / size;
		scaledWidth = scaledWidth == 0 ? 1 : scaledWidth;
		int scaledHeight = imageData.getHeight() / size;
		scaledHeight = scaledHeight == 0 ? 1 : scaledHeight;
		
		Image scaledImage = imageData.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		Image finalImage = scaledImage.getScaledInstance(imageData.getWidth(), imageData.getHeight(), Image.SCALE_FAST);
		
		Graphics2D graphics = imageData.createGraphics();
		graphics.drawImage(finalImage, x, y, x + width, y + height, x, y, x + width, y + height, null);
		graphics.dispose();
	}
	
}
