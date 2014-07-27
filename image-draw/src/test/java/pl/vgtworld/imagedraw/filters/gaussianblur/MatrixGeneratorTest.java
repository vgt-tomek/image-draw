package pl.vgtworld.imagedraw.filters.gaussianblur;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MatrixGeneratorTest {
	
	@Test
	public void shouldCreateValidMatrix() {
		MatrixGenerator generator = new MatrixGenerator();
		
		float[][] matrix = generator.createMatrix(2, 1);
		int matrixWidth = matrix[0].length;
		int matrixHeight = matrix.length;
		
		// Size check.
		assertThat(matrixWidth).isEqualTo(5);
		assertThat(matrixHeight).isEqualTo(5);
		// Symmetry check.
		assertThat(matrix[0][0]).isEqualTo(matrix[4][4]);
		assertThat(matrix[1][0]).isEqualTo(matrix[3][0]);
		assertThat(matrix[0][1]).isEqualTo(matrix[0][3]);
		// Normal distribution check.
		assertThat(matrix[0][0]).isLessThan(matrix[1][1]);
		assertThat(matrix[1][1]).isLessThan(matrix[2][2]);
		assertThat(matrix[2][2]).isGreaterThan(matrix[3][3]);
		assertThat(matrix[3][3]).isGreaterThan(matrix[4][4]);
	}
	
}
