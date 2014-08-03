package pl.vgtworld.imagedraw.filters.matrix;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

/**
 * Filter applying convolution matrix to image.
 */
public class MatrixFilter implements ImageDrawFilter {
	
	private ImageEdgeResizeHelper edgeResizeHelper = new ImageEdgeResizeHelper();
	
	private float[][] matrix;
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param matrix Convolution matrix to apply on image.
	 */
	public MatrixFilter(float[][] matrix) {
		this.matrix = new float[matrix.length][];
		for (int i = 0; i < matrix.length; ++i) {
			this.matrix[i] = new float[matrix[i].length];
			System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
		}
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[] kernelArray = MatrixConverter.convertToOneDimensionalArray(matrix);
		Kernel kernelMatrix = new Kernel(matrix[0].length, matrix.length, kernelArray);
		ConvolveOp convolve = new ConvolveOp(kernelMatrix);
		int requiredEdgeCount = matrix[0].length > matrix.length ? matrix[0].length : matrix.length;
		BufferedImage imageWithEdges = edgeResizeHelper.addEdgesToImage(image.getImage(), requiredEdgeCount);
		BufferedImage convolvedImageWithEdges = convolve.filter(imageWithEdges, null);
		BufferedImage convolvedImage = edgeResizeHelper.removeEdgesFromImage(convolvedImageWithEdges, requiredEdgeCount);
		copyImageArea(convolvedImage, image.getImage(), x, y, width, height);
	}
	
	private void copyImageArea(BufferedImage source, BufferedImage destination, int x, int y, int width, int height) {
		Graphics2D graphics = destination.createGraphics();
		graphics.drawImage(source, x, y, x + width, y + height, x, y, x + width, y + height, null);
		graphics.dispose();
	}
}
