package pl.vgtworld.imagedraw.filters.gaussianblur;

class MatrixGenerator {
	
	float[][] createMatrix(int radius, double standardDeviation) {
		if (radius < 1) {
			throw new IllegalArgumentException("Radius must be greater than 0.");
		}
		int matrixSize = radius * 2 + 1;
		float[][] matrix = new float[matrixSize][matrixSize];
		populateMatrixWithValues(matrix, radius, standardDeviation);
		return matrix;
	}
	
	private void populateMatrixWithValues(float[][] matrix, int radius, double standardDeviation) {
		int matrixSize = matrix.length;
		for (int j = 0; j < matrixSize; ++j) {
			for (int i = 0; i < matrixSize; ++i) {
				int xDistanceFromCenter = Math.abs(i - radius);
				int yDistanceFromCenter = Math.abs(j - radius);
				float value = calculateGaussianBlurCellValue(
						xDistanceFromCenter,
						yDistanceFromCenter,
						standardDeviation
						);
				matrix[i][j] = value;
			}
		}
	}
	
	private float calculateGaussianBlurCellValue(int xDistance, int yDistance, double standardDeviation) {
		double result = 1.0 / (2 * Math.PI * Math.pow(standardDeviation, 2));
		double ePower = -((Math.pow(xDistance, 2) + Math.pow(yDistance, 2)) / (2 * Math.pow(standardDeviation, 2)));
		result = result * Math.exp(ePower);
		return (float) result;
	}
	
}
