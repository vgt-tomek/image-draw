package pl.vgtworld.imagedraw.filters.gaussianblur;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.MatrixNormalizeHelper;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

/**
 * Filter applying gaussian blur to image.
 */
@SuppressWarnings("WeakerAccess")
public class GaussianBlurFilter implements ImageDrawFilter {
	
	private MatrixGenerator matrixGenerator = new MatrixGenerator();
	
	private MatrixNormalizeHelper matrixNormalizeHelper = new MatrixNormalizeHelper();
	
	private int radius;
	
	private double standardDeviation;
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * <p>
	 * Standard deviation is set to half of provided radius.
	 * 
	 * @param radius Gaussian blur radius.
	 */
	public GaussianBlurFilter(int radius) {
		this.radius = radius;
		standardDeviation = radius / 2.0f;
	}
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param radius Gaussian blur radius.
	 * @param standardDeviation Gaussian blur standard deviation.
	 */
	public GaussianBlurFilter(int radius, double standardDeviation) {
		this.radius = radius;
		this.standardDeviation = standardDeviation;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[][] matrix = matrixGenerator.createMatrix(radius, standardDeviation);
		matrix = matrixNormalizeHelper.normalizeMatrix(matrix);
		MatrixFilter matrixFilter = new MatrixFilter(matrix);
		matrixFilter.doFilter(image, x, y, width, height);
	}
	
}
