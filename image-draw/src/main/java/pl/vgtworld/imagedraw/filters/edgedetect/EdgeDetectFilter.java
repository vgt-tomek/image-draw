package pl.vgtworld.imagedraw.filters.edgedetect;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

/**
 * Filter applying edge detection to image.
 */
@SuppressWarnings("WeakerAccess")
public class EdgeDetectFilter implements ImageDrawFilter {
	
	private static final int DEFAULT_STRENGTH = 1;
	
	private float strength;
	
	/**
	 * Creates filter with default configuration.
	 * 
	 * <p>
	 * Edge detection strength is set to 1.
	 */
	public EdgeDetectFilter() {
		strength = DEFAULT_STRENGTH;
	}
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param strength Edge detection strength.
	 */
	public EdgeDetectFilter(float strength) {
		this.strength = strength;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[][] matrix = new float[][] {
				{ 0, strength, 0 },
				{ strength, -4 * strength, strength },
				{ 0, strength, 0 }
		};
		MatrixFilter filter = new MatrixFilter(matrix);
		filter.doFilter(image, x, y, width, height);
	}
	
}
