package pl.vgtworld.imagedraw.processing;

import java.awt.Image;
import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageResizeActions {
	
	void resize(ImageDrawEntity image, Integer newWidth, Integer newHeight) {
		dimensionValidation(newWidth, newHeight);
		int widthNullChecked = (newWidth == null) ? calculateNewWidth(image, newHeight) : newWidth;
		int heightNullChecked = (newHeight == null) ? calculateNewHeight(image, newWidth) : newHeight;
		BufferedImage currentImageData = image.getImage();
		Image scaledInstance = currentImageData.getScaledInstance(
				widthNullChecked,
				heightNullChecked,
				Image.SCALE_SMOOTH
				);
		BufferedImage resizedImageData = new BufferedImage(
				widthNullChecked,
				heightNullChecked,
				image.getImage().getType()
				);
		resizedImageData.getGraphics().drawImage(scaledInstance, 0, 0, null);
		image.setImage(resizedImageData);
	}
	
	private int calculateNewWidth(ImageDrawEntity image, int newHeight) {
		return calculateNewEdgeLength(image.getImage().getWidth(), image.getImage().getHeight(), newHeight);
	}
	
	private int calculateNewHeight(ImageDrawEntity image, int newWidth) {
		return calculateNewEdgeLength(image.getImage().getHeight(), image.getImage().getWidth(), newWidth);
	}
	
	private int calculateNewEdgeLength(int currentEdgeLength, int currentOtherEdgeLength, int newOtherEdgeLength) {
		float resizeRatio = newOtherEdgeLength / (float) currentOtherEdgeLength;
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
