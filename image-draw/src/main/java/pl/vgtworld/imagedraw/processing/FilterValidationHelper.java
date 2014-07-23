package pl.vgtworld.imagedraw.processing;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class FilterValidationHelper {
	
	void validateParameters(ImageDrawEntity image, int x, int y, int width, int height) {
		validateImage(image);
		int imageWidth = image.getImage().getWidth();
		int imageHeight = image.getImage().getHeight();
		validateUpperLeftCorner(x, y);
		validateWidth(x, width, imageWidth);
		validateHeight(y, height, imageHeight);
	}
	
	private void validateImage(ImageDrawEntity image) {
		if (image == null) {
			throw new IllegalStateException("Image parameter is required.");
		}
		if (image.getImage() == null) {
			throw new IllegalStateException("Image object does not contain image data.");
		}
	}
	
	private void validateUpperLeftCorner(int x, int y) {
		if (x < 0) {
			throw new IllegalArgumentException("X coordinate must be greater or equal to 0.");
		}
		if (y < 0) {
			throw new IllegalArgumentException("Y coordinate must be greater or equal to 0.");
		}
	}
	
	private void validateWidth(int x, int width, int imageWidth) {
		if (width <= 0) {
			throw new IllegalArgumentException("Width must be greater than 0.");
		}
		if (width > imageWidth || x + width > imageWidth) {
			throw new IllegalArgumentException("Width of defined area is outside of image.");
		}
	}
	
	private void validateHeight(int y, int height, int imageHeight) {
		if (height <= 0) {
			throw new IllegalArgumentException("Height must be greater than 0.");
		}
		if (height > imageHeight || y + height > imageHeight) {
			throw new IllegalArgumentException("Height of defined area is outside of image.");
		}
	}
	
}
