package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageThumbnailScaleActions {
	
	private ImageResizeActions resizeActions;
	
	public ImageThumbnailScaleActions(ImageResizeActions resizeActions) {
		this.resizeActions = resizeActions;
	}
	
	public void thumbnail(ImageDrawEntity image, int width, int height, Color backgroundColor) {
		validateParameters(width, height);
		
		BufferedImage bufferedImage = image.getImage();
		
		if (!isImageAlreadySmallEnough(width, height, bufferedImage)) {
			double xRatio = bufferedImage.getWidth() / (double) width;
			double yRatio = bufferedImage.getHeight() / (double) height;
			if (xRatio >= yRatio) {
				resizeActions.resize(image, width, null);
			} else {
				resizeActions.resize(image, null, height);
			}
		}
		
		if (backgroundColor != null) {
			fillWithBackgroundColor(image, width, height, backgroundColor);
		}
	}
	
	private void fillWithBackgroundColor(ImageDrawEntity image, int width, int height, Color backgroundColor) {
		BufferedImage finalImage = new BufferedImage(width, height, image.getImage().getType());
		int xOffset = (width - image.getImage().getWidth()) / 2;
		int yOffset = (height - image.getImage().getHeight()) / 2;
		Graphics2D g = finalImage.createGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		g.drawImage(image.getImage(), xOffset, yOffset, null);
		image.setImage(finalImage);
	}
	
	private void validateParameters(int width, int height) {
		if (width <= 0) {
			throw new IllegalArgumentException("Width cannot be negative.");
		}
		if (height <= 0) {
			throw new IllegalArgumentException("height cannot be negative.");
		}
	}
	
	private boolean isImageAlreadySmallEnough(int width, int height, BufferedImage bufferedImage) {
		return bufferedImage.getWidth() <= width && bufferedImage.getHeight() <= height;
	}
	
}
