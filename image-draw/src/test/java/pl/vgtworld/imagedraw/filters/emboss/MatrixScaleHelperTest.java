package pl.vgtworld.imagedraw.filters.emboss;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MatrixScaleHelperTest {
	
	private float[][] matrix = {
			{ 2, 1, 0 },
			{ 1, 1, -1 },
			{ 0, -1, -2 }
	};
	
	private MatrixScaleHelper helper = new MatrixScaleHelper();
	
	@Test
	public void shouldNotChangeMatrixWhenStrengthIsEqualToOne() {
		float[][] result = helper.scaleMatrix(matrix, 1);
		
		assertThat(result).isEqualTo(matrix);
	}
	
	@Test
	public void shouldScaleMatrixValues() {
		float[][] result = helper.scaleMatrix(matrix, 2);
		
		assertThat(result).isEqualTo(new float[][] {
				{ 4, 2, 0 },
				{ 2, 1, -2 },
				{ 0, -2, -4 }
		});
	}
}
