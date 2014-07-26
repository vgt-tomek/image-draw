package pl.vgtworld.imagedraw.filters.matrix;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MatrixConverterTest {
	
	@Test
	public void shouldConvertSquareMatrixToArray() {
		float[][] matrix = createMatrix(3, 3);
		
		float[] result = MatrixConverter.convertToOneDimensionalArray(matrix);
		
		assertThat(result).isEqualTo(new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
	}
	
	@Test
	public void shouldConvertOneDimensionalRowToArray() {
		float[][] matrix = createMatrix(4, 1);
		
		float[] result = MatrixConverter.convertToOneDimensionalArray(matrix);
		
		assertThat(result).isEqualTo(new float[] {1, 2, 3, 4});
	}
	
	@Test
	public void shouldConvertOneDimensionalColumnToArray() {
		float[][] matrix = createMatrix(1, 4);
		
		float[] result = MatrixConverter.convertToOneDimensionalArray(matrix);
		
		assertThat(result).isEqualTo(new float[] {1, 2, 3, 4});
	}
	
	@Test
	public void shouldConvertHorizontalMatrixToArray() {
		float[][] matrix = createMatrix(4, 2);
		
		float[] result = MatrixConverter.convertToOneDimensionalArray(matrix);
		
		assertThat(result).isEqualTo(new float[] {1, 2, 3, 4, 5, 6, 7, 8});
	}
	
	@Test
	public void shouldConvertVerticalMatrixToArray() {
		float[][] matrix = createMatrix(2, 4);
		
		float[] result = MatrixConverter.convertToOneDimensionalArray(matrix);
		
		assertThat(result).isEqualTo(new float[] {1, 2, 3, 4, 5, 6, 7, 8});
	}
	
	private float[][] createMatrix(int width, int height) {
		float[][] matrix = new float[width][height];
		int number = 0;
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				matrix[i][j] = ++number;
			}
		}
		return matrix;
	}
}
