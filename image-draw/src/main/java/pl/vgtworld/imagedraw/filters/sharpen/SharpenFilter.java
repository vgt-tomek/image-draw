package pl.vgtworld.imagedraw.filters.sharpen;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

/**
 * Filter applying sharpening to image.
 */
public class SharpenFilter implements ImageDrawFilter {
	
	private float strength = 1;

	/**
	 * Creates filter with default configuration.
	 * 
	 * <p>
	 * Sharpening strength is set to 1.
	 */
	public SharpenFilter() {
	}
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param strength Sharpening strength.
	 */
	public SharpenFilter(float strength) {
		this.strength = strength;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		float[][] matrix = {
				{ -strength, -strength, -strength },
				{ -strength, 1 + 8 * strength, -strength },
				{ -strength, -strength, -strength }
		};
		MatrixFilter filter = new MatrixFilter(matrix);
		filter.doFilter(image, x, y, width, height);
	}
	
}
