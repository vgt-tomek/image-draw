package pl.vgtworld.imagedraw.filters;

/**
 * Matrix normalize helper.
 */
public class MatrixNormalizeHelper {
	
	/**
	 * Normalizes matrix values (scaling them up or down), so that sum of all
	 * elements is as close as possible to 1.
	 * 
	 * @param matrix Matrix to normalize.
	 * @return Normalized matrix.
	 */
	public float[][] normalizeMatrix(float[][] matrix) {
		double sum = sumMatrix(matrix);
		double modifier = 1 / sum;
		return normalize(matrix, (float) modifier);
	}
	
	private float[][] normalize(float[][] matrix, float modifier) {
		float[][] normalizedMatrix = new float[matrix.length][matrix[0].length];
		for (int j = 0; j < matrix[0].length; ++j) {
			for (int i = 0; i < matrix.length; ++i) {
				normalizedMatrix[i][j] = matrix[i][j] * modifier;
			}
		}
		return normalizedMatrix;
	}
	
	private double sumMatrix(float[][] matrix) {
		double sum = 0;
		for (float[] row : matrix) {
			for (float cell : row) {
				sum += cell;
			}
		}
		return sum;
	}
	
}
