package pl.vgtworld.imagedraw.filters.emboss;

import java.util.HashMap;
import java.util.Map;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

public class EmbossFilter implements ImageDrawFilter {
	
	private static final int MATRICES_SIZE = 3;
	
	private static final Map<EmbossDirection, float[][]> matrices = new HashMap<>();
	
	private EmbossDirection direction;
	
	private float strength;
	
	static {
		matrices.put(EmbossDirection.LOWER_RIGHT, new float[][] {
				{ -2, -1, 0 },
				{ -1, 0, 1 },
				{ 0, 1, 2 }
		});
		matrices.put(EmbossDirection.LOWER_LEFT, new float[][] {
				{ 0, -1, -2 },
				{ 1, 0, -1 },
				{ 2, 1, 0 }
		});
		matrices.put(EmbossDirection.UPPER_LEFT, new float[][] {
				{ 2, 1, 0 },
				{ 1, 0, -1 },
				{ 0, -1, -2 }
		});
		matrices.put(EmbossDirection.UPPER_RIGHT, new float[][] {
				{ 0, 1, 2 },
				{ -1, 0, 1 },
				{ -2, -1, 0 }
		});
	}
	
	public EmbossFilter(EmbossDirection direction) {
		this.direction = direction;
	}
	
	public EmbossFilter(EmbossDirection direction, float strength) {
		this.direction = direction;
		this.strength = strength;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		if (direction == null) {
			throw new IllegalStateException("Direction is not defined.");
		}
		float[][] matrix = matrices.get(direction);
		matrix = scaleMatrix(matrix);
		MatrixFilter filter = new MatrixFilter(matrix);
		filter.doFilter(image, x, y, width, height);
	}
	
	private float[][] scaleMatrix(float[][] matrix) {
		float[][] scaledMatrix = new float[MATRICES_SIZE][MATRICES_SIZE];
		for (int i = 0; i < MATRICES_SIZE; ++i) {
			for (int j = 0; j < MATRICES_SIZE; ++j) {
				scaledMatrix[i][j] = matrix[i][j] * strength;
			}
		}
		scaledMatrix[MATRICES_SIZE / 2][MATRICES_SIZE / 2] = 1;
		return scaledMatrix;
	}
}
