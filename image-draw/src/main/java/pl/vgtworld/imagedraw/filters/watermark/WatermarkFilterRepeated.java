package pl.vgtworld.imagedraw.filters.watermark;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class WatermarkFilterRepeated extends WatermarkFilter {
	
	private ImageDrawEntity watermark;
	
	private int xOffset;
	
	private int yOffset;
	
	private int horizontalSpacing;
	
	private int verticalSpacing;
	
	WatermarkFilterRepeated(
			ImageDrawEntity watermark,
			int xOffset, int yOffset, int horizontalSpacing, int verticalSpacing) {
		this.watermark = watermark;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horizontalSpacing = horizontalSpacing;
		this.verticalSpacing = verticalSpacing;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage imageCopy = new BufferedImage(
				image.getImage().getWidth(),
				image.getImage().getHeight(),
				image.getImage().getType()
				);
		Graphics2D graphics = imageCopy.createGraphics();
		graphics.drawImage(image.getImage(), 0, 0, null);
		
		BufferedImage watermarkData = watermark.getImage();
		int watermarkWidth = watermark.getImage().getWidth();
		int watermarkheight = watermark.getImage().getHeight();
		normalizeOffsets();
		for (int j = yOffset; j < imageCopy.getHeight(); j += verticalSpacing + watermarkheight) {
			for (int i = xOffset; i < imageCopy.getWidth(); i += horizontalSpacing + watermarkWidth) {
				graphics.drawImage(watermarkData, i, j, null);
			}
		}
		graphics.dispose();
		
		Graphics2D imageGraphics = image.getImage().createGraphics();
		imageGraphics.drawImage(imageCopy, x, y, x + width, y + height, x, y, x + width, y + height, null);
	}
	
	private void normalizeOffsets() {
		normalizeXOffset();
		normalizeYOffset();
	}

	private void normalizeXOffset() {
		int watermarkWidth = watermark.getImage().getWidth();
		while (xOffset <= -(horizontalSpacing + watermarkWidth)) {
			xOffset += (horizontalSpacing + watermarkWidth);
		}
		while (xOffset >= horizontalSpacing - watermarkWidth) {
			xOffset -= (horizontalSpacing + watermarkWidth);
		}
	}

	private void normalizeYOffset() {
		int watermarkHeight = watermark.getImage().getHeight();
		while (yOffset <= -(verticalSpacing + watermarkHeight)) {
			yOffset += (verticalSpacing + watermarkHeight);
		}
		while (yOffset >= verticalSpacing - watermarkHeight) {
			yOffset -= (verticalSpacing + watermarkHeight);
		}
	}
}
