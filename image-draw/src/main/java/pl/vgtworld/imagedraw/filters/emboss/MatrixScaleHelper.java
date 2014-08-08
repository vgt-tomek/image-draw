package pl.vgtworld.imagedraw.filters.emboss;

public class MatrixScaleHelper {
	
	float[][] scaleMatrix(float[][] matrix, float strength) {
		
		float[][] scaledMatrix = new float[matrix.length][];
		for (int i = 0; i < matrix.length; ++i) {
			scaledMatrix[i] = new float[matrix[i].length];
			for (int j = 0; j < matrix[i].length; ++j) {
				scaledMatrix[i][j] = matrix[i][j] * strength;
			}
		}
		return scaledMatrix;
	}
	
}
