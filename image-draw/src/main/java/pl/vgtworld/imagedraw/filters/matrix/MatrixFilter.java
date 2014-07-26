package pl.vgtworld.imagedraw.filters.matrix;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public class MatrixFilter implements ImageDrawFilter {
	
	private float[][] matrix;
	
	public MatrixFilter(float[][] matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[] kernelArray = MatrixConverter.convertToOneDimensionalArray(matrix);
		Kernel kernelMatrix = new Kernel(matrix[0].length, matrix.length, kernelArray);
		ConvolveOp convolve = new ConvolveOp(kernelMatrix);
		BufferedImage convolvedImage = convolve.filter(image.getImage(), null);
		copyImageArea(convolvedImage, image.getImage(), x, y, width, height);
	}
	
	private void copyImageArea(BufferedImage source, BufferedImage destination, int x, int y, int width, int height) {
		Graphics2D graphics = destination.createGraphics();
		graphics.drawImage(source, x, y, x + width, y + height, x, y, x + width, y + height, null);
		graphics.dispose();
	}
}
