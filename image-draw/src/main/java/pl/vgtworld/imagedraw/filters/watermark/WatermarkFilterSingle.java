package pl.vgtworld.imagedraw.filters.watermark;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class WatermarkFilterSingle extends WatermarkFilter {
	
	private ImageDrawEntity watermark;
	
	private WatermarkLocation location;
	
	private Integer margin;
	
	WatermarkFilterSingle(ImageDrawEntity watermark, WatermarkLocation location, Integer margin) {
		this.watermark = watermark;
		this.location = location;
		this.margin = margin;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage watermarkImage = watermark.getImage();
		int watermarkWidth = watermarkImage.getWidth();
		int watermarkHeight = watermarkImage.getHeight();
		
		int watermarkX = calculateWatermarkXPosition(x, width, watermarkWidth);
		int watermarkY = calculateWatermarkYPosition(y, height, watermarkHeight);
		
		Graphics2D graphics = image.getImage().createGraphics();
		graphics.drawImage(watermarkImage, watermarkX, watermarkY, null);
		graphics.dispose();
	}
	
	private int calculateWatermarkXPosition(int filterX, int imageWidth, int watermarkWidth) {
		int watermarkX = 0;
		if (location == WatermarkLocation.UPPER_LEFT || location == WatermarkLocation.LOWER_LEFT) {
			watermarkX = filterX + margin;
		} else if (location == WatermarkLocation.UPPER_RIGHT || location == WatermarkLocation.LOWER_RIGHT) {
			watermarkX = imageWidth - watermarkWidth - margin;
		} else {
			watermarkX = (imageWidth - watermarkWidth) / 2;
		}
		return watermarkX;
	}
	
	private int calculateWatermarkYPosition(int filterY, int imageHeight, int watermarkHeight) {
		int watermarkY = 0;
		if (location == WatermarkLocation.UPPER_LEFT || location == WatermarkLocation.UPPER_RIGHT) {
			watermarkY = filterY + margin;
		} else if (location == WatermarkLocation.LOWER_LEFT || location == WatermarkLocation.LOWER_RIGHT) {
			watermarkY = imageHeight - watermarkHeight - margin;
		} else {
			watermarkY = (imageHeight - watermarkHeight) / 2;
		}
		return watermarkY;
	}
	
}
