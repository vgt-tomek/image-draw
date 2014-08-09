package pl.vgtworld.imagedraw.filters.edgedetect;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

public class EdgeDetectFilter implements ImageDrawFilter {
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[][] matrix = new float[][] {
				{ 0, 1, 0 },
				{ 1, -4, 1 },
				{ 0, 1, 0 }
		};
		MatrixFilter filter = new MatrixFilter(matrix);
		filter.doFilter(image, x, y, width, height);
	}
	
}
