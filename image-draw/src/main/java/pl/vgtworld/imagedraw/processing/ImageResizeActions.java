package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.Image;

class ImageResizeActions {
	
	public void resize(Image image, Integer newWidth, Integer newHeight) {
		dimensionValidation(newWidth, newHeight);
		if (newWidth == null) {
			newWidth = calculateNewWidth(image, newHeight);
		}
		if (newHeight == null) {
			newHeight = calculateNewHeight(image, newWidth);
		}
		BufferedImage currentImageData = image.getImage();
		java.awt.Image scaledInstance = currentImageData.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
		BufferedImage resizedImageData = new BufferedImage(newWidth, newHeight, image.getImage().getType());
		resizedImageData.getGraphics().drawImage(scaledInstance, 0, 0, null);
		image.setImage(resizedImageData);
	}

	private int calculateNewWidth(Image image, int newHeight) {
		return calculateNewEdgeLength(image.getImage().getWidth(), image.getImage().getHeight(), newHeight);
	}
	
	private int calculateNewHeight(Image image, int newWidth) {
		return calculateNewEdgeLength(image.getImage().getHeight(), image.getImage().getWidth(), newWidth);
	}

	private int calculateNewEdgeLength(int currentEdgeLength, int currentOtherEdgeLength, int newOtherEdgeLength) {
		float resizeRatio = newOtherEdgeLength / (float)currentOtherEdgeLength;
		float calculatedLength = currentEdgeLength * resizeRatio;
		int roundedLength = Math.round(calculatedLength);
		return roundedLength == 0 ? 1 : roundedLength;
	}

	private void dimensionValidation(Integer newWidth, Integer newHeight) {
		if (newWidth == null && newHeight == null) {
			throw new IllegalStateException("At least one dimension must not be null");
		}
		if ((newWidth != null && newWidth <= 0) || (newHeight != null && newHeight <= 0)) {
			throw new IllegalArgumentException("Dimension cannot be negative");
		}
	}
	
}
