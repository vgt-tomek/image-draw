package pl.vgtworld.imagedraw.filters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MatrixNormalizeHelperTest {
	
	private MatrixNormalizeHelper helper = new MatrixNormalizeHelper();
	
	@Test
	public void shouldDownscaleSquareMatrix() {
		float[][] matrix = new float[][] {
				{ 1, 1, 1 },
				{ 1, 1, 1 },
				{ 1, 1, 1 }
		};
		
		float[][] result = helper.normalizeMatrix(matrix);
		
		assertThat(result.length).isEqualTo(3);
		assertThat(result[0].length).isEqualTo(3);
		assertThat(result[0][0]).isBetween(0.11f, 0.12f);
	}
	
	@Test
	public void shouldUpscaleSquareMatrix() {
		float[][] matrix = new float[][] {
				{ 0.1f, 0.1f, 0.1f },
				{ 0.1f, 0.1f, 0.1f },
				{ 0.1f, 0.1f, 0.1f }
		};
		
		float[][] result = helper.normalizeMatrix(matrix);
		
		assertThat(result.length).isEqualTo(3);
		assertThat(result[0].length).isEqualTo(3);
		assertThat(result[0][0]).isBetween(0.11f, 0.12f);
	}
	
	@Test
	public void shouldNormalizeHorizontalMatrix() {
		float[][] matrix = new float[][] {
				{ 1, 1 },
				{ 1, 1 },
				{ 1, 1 },
				{ 1, 1 },
				{ 1, 1 }
		};
		
		float[][] result = helper.normalizeMatrix(matrix);
		
		assertThat(result.length).isEqualTo(5);
		assertThat(result[0].length).isEqualTo(2);
		assertThat(result[0][0]).isEqualTo(0.1f);
	}
	
	@Test
	public void shouldNormalizeVerticalMatrix() {
		float[][] matrix = new float[][] {
				{ 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 }
		};
		
		float[][] result = helper.normalizeMatrix(matrix);
		
		assertThat(result.length).isEqualTo(2);
		assertThat(result[0].length).isEqualTo(5);
		assertThat(result[0][0]).isEqualTo(0.1f);
	}
	
}
