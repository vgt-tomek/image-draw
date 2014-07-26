package pl.vgtworld.imagedraw.filters.matrix;

final class MatrixConverter {
	
	private MatrixConverter() {
	}
	
	static float[] convertToOneDimensionalArray(float[][] matrix) {
		float[] convertedMatrix = new float[matrix.length * matrix[0].length];
		int convertedMatrixIndex = 0;
		for (int j = 0; j < matrix[0].length; ++j) {
			for (int i = 0; i < matrix.length; ++i) {
				convertedMatrix[convertedMatrixIndex++] = matrix[i][j];
			}
		}
		return convertedMatrix;
	}
	
}
