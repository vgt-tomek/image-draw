package pl.vgtworld.imagedraw.filters.gaussianblur;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

public class GaussianBlurFilter implements ImageDrawFilter {
	
	private MatrixGenerator matrixGenerator = new MatrixGenerator();
	
	private int radius;
	
	private double standardDeviation;
	
	public GaussianBlurFilter(int radius) {
		this.radius = radius;
		standardDeviation = radius / 2.0f;
	}
	
	public GaussianBlurFilter(int radius, double standardDeviation) {
		this.radius = radius;
		this.standardDeviation = standardDeviation;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[][] matrix = matrixGenerator.createMatrix(radius, standardDeviation);
		MatrixFilter matrixFilter = new MatrixFilter(matrix);
		matrixFilter.doFilter(image, x, y, width, height);
	}
	
}
