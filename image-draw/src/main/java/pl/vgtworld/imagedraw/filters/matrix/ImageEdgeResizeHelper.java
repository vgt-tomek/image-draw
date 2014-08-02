package pl.vgtworld.imagedraw.filters.matrix;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class ImageEdgeResizeHelper {
	
	BufferedImage addEdgesToImage(BufferedImage sourceImage, int edgeSize) {
		BufferedImage finalImage = new BufferedImage(
				sourceImage.getWidth() + 2 * edgeSize,
				sourceImage.getHeight() + 2 * edgeSize,
				sourceImage.getType()
				);
		Graphics2D graphics = finalImage.createGraphics();
		graphics.drawImage(sourceImage, edgeSize, edgeSize, null);
		for (int i = 1; i <= edgeSize; ++i) {
			graphics.copyArea(0, edgeSize, finalImage.getWidth(), 1, 0, -i);
			graphics.copyArea(0, sourceImage.getHeight() + edgeSize - 1, finalImage.getWidth(), 1, 0, i);
			graphics.copyArea(edgeSize, 0, 1, finalImage.getHeight(), -i, 0);
			graphics.copyArea(sourceImage.getWidth() + edgeSize - 1, 0, 1, finalImage.getWidth(), i, 0);
		}
		graphics.dispose();
		return finalImage;
	}

	BufferedImage removeEdgesFromImage(BufferedImage sourceImage, int edgeSize) {
		int finalImageWidth = sourceImage.getWidth() - 2 * edgeSize;
		int finalImageHeight = sourceImage.getHeight() - 2 * edgeSize;
		BufferedImage finalImage = new BufferedImage(finalImageWidth, finalImageHeight, sourceImage.getType());
		
		Graphics2D graphics = finalImage.createGraphics();
		graphics.drawImage(
				sourceImage,
				0, 0,
				finalImageWidth, finalImageHeight,
				edgeSize, edgeSize,
				edgeSize + finalImageWidth, edgeSize + finalImageHeight,
				null
				);
		graphics.dispose();
		return finalImage;
	}
	
}
