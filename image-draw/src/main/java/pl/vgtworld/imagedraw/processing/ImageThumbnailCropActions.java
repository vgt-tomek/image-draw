package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageThumbnailCropActions {
	
	private ImageResizeActions resizeActions;
	
	private ImageCropActions cropActions;
	
	public ImageThumbnailCropActions(ImageResizeActions resizeActions, ImageCropActions cropActions) {
		this.resizeActions = resizeActions;
		this.cropActions = cropActions;
	}
	
	public void thumbnail(ImageDrawEntity image, int width, int height) {
		validateParameters(width, height);
		BufferedImage bufferedImage = image.getImage();
		double xRatio = bufferedImage.getWidth() / (double) width;
		double yRatio = bufferedImage.getHeight() / (double) height;
		if (xRatio > yRatio) {
			resizeActions.resize(image, null, height);
			cropImageHorizontally(image, width);
		} else {
			resizeActions.resize(image, width, null);
			cropImageVertically(image, height);
		}
	}
	
	private void cropImageHorizontally(ImageDrawEntity image, int desiredWidth) {
		if (image.getImage().getWidth() > desiredWidth) {
			int pixelDifference = image.getImage().getWidth() - desiredWidth;
			cropActions.crop(image, pixelDifference / 2, 0, desiredWidth, image.getImage().getHeight());
		}
	}
	
	private void cropImageVertically(ImageDrawEntity image, int desiredHeight) {
		if (image.getImage().getHeight() > desiredHeight) {
			int pixelDifference = image.getImage().getHeight() - desiredHeight;
			cropActions.crop(image, 0, pixelDifference / 2, image.getImage().getWidth(), desiredHeight);
		}
	}
	
	private void validateParameters(int width, int height) {
		if (width <= 0) {
			throw new IllegalArgumentException("Invalid width value. width=" + width);
		}
		if (height <= 0) {
			throw new IllegalArgumentException("Invalid height value. height=" + height);
		}
	}
}
