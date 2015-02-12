package pl.vgtworld.imagedraw.filters.emboss;

import java.util.HashMap;
import java.util.Map;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

/**
 * Filter applying emboss effect to image.
 */
public class EmbossFilter implements ImageDrawFilter {
	
	private static final Map<EmbossDirection, float[][]> MATRICES = new HashMap<>();
	
	private EmbossDirection direction;
	
	private float strength;
	
	static {
		MATRICES.put(EmbossDirection.LOWER_RIGHT, new float[][]{
				{-2, -1, 0},
				{-1, 1, 1},
				{0, 1, 2}
		});
		MATRICES.put(EmbossDirection.LOWER_LEFT, new float[][]{
				{0, -1, -2},
				{1, 1, -1},
				{2, 1, 0}
		});
		MATRICES.put(EmbossDirection.UPPER_LEFT, new float[][]{
				{2, 1, 0},
				{1, 1, -1},
				{0, -1, -2}
		});
		MATRICES.put(EmbossDirection.UPPER_RIGHT, new float[][]{
				{0, 1, 2},
				{-1, 1, 1},
				{-2, -1, 0}
		});
	}

	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param direction Effect direction.
	 */
	public EmbossFilter(EmbossDirection direction) {
		this.direction = direction;
	}
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * @param direction Effect direction.
	 * @param strength Effect strength.
	 */
	public EmbossFilter(EmbossDirection direction, float strength) {
		this.direction = direction;
		this.strength = strength;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		if (direction == null) {
			throw new IllegalStateException("Direction is not defined.");
		}
		float[][] matrix = MATRICES.get(direction);
		if (strength != 1) {
			MatrixScaleHelper scaleHelper = new MatrixScaleHelper();
			matrix = scaleHelper.scaleMatrix(matrix, strength);
		}
		MatrixFilter filter = new MatrixFilter(matrix);
		filter.doFilter(image, x, y, width, height);
	}
	
}
