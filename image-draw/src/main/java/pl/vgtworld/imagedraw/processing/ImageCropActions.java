package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageCropActions {
	
	public void crop(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage bufferedImage = image.getImage();
		validateParameters(bufferedImage, x, y, width, height);
		BufferedImage subimage = bufferedImage.getSubimage(x, y, width, height);
		image.setImage(subimage);
	}
	
	private void validateParameters(BufferedImage image, int x, int y, int width, int height) {
		if (x < 0 || x >= image.getWidth()) {
			String message = "X should not be smaller than zero or greater than image width. X=" + x;
			throw new IllegalArgumentException(message);
		}
		if (y < 0 || y >= image.getHeight()) {
			String message = "Y should not be smaller than zero or greater than image height. Y=" + y;
			throw new IllegalArgumentException(message);
		}
		if (width <= 0 || x + width > image.getWidth()) {
			String message = "Width should not be smaller than zero or bigger than available image width. width=" + width;
			throw new IllegalArgumentException(message);
		}
		if (height <= 0 || y + height > image.getHeight()) {
			String message = "Height should not be smaller than zero or bigger than available image height. height="
					+ height;
			throw new IllegalArgumentException(message);
		}
	}
	
}
