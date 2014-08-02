package pl.vgtworld.imagedraw.filters.matrix;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class MatrixFilterTest {
	
	@Test
	public void shouldNotMakeAnyChangesToImage() throws IOException {
		ImageDrawEntity imageEntity = createEntity();
		float[][] matrix = new float[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		MatrixFilter filter = new MatrixFilter(matrix);
		
		int leftPixelBefore = imageEntity.getImage().getRGB(25, 50);
		int rightPixelBefore = imageEntity.getImage().getRGB(75, 50);
		filter.doFilter(imageEntity, 50, 50, 50, 100);
		int leftPixelAfter = imageEntity.getImage().getRGB(25, 50);
		int rightPixelAfter = imageEntity.getImage().getRGB(75, 50);
		
		assertThat(leftPixelBefore).isEqualTo(leftPixelAfter);
		assertThat(rightPixelBefore).isEqualTo(rightPixelAfter);
	}
	
	@Test
	public void shouldNotMakeAnyChangesToEdgesOfImage() throws IOException {
		ImageDrawEntity imageEntity = createEntity();
		float[][] matrix = new float[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		MatrixFilter filter = new MatrixFilter(matrix);
		
		int leftPixelBefore = imageEntity.getImage().getRGB(0, 0);
		int rightPixelBefore = imageEntity.getImage().getRGB(99, 99);
		filter.doFilter(imageEntity, 50, 50, 50, 100);
		int leftPixelAfter = imageEntity.getImage().getRGB(0, 0);
		int rightPixelAfter = imageEntity.getImage().getRGB(99, 99);
		
		assertThat(leftPixelBefore).isEqualTo(leftPixelAfter);
		assertThat(rightPixelBefore).isEqualTo(rightPixelAfter);
	}
	
	@Test
	public void shouldConvertRightHalfOfImage() throws IOException {
		ImageDrawEntity imageEntity = createEntity();
		float[][] matrix = new float[][] { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
		MatrixFilter filter = new MatrixFilter(matrix);
		
		int leftPixelBefore = imageEntity.getImage().getRGB(25, 50);
		int rightPixelBefore = imageEntity.getImage().getRGB(75, 50);
		filter.doFilter(imageEntity, 50, 50, 50, 100);
		int leftPixelAfter = imageEntity.getImage().getRGB(25, 50);
		int rightPixelAfter = imageEntity.getImage().getRGB(75, 50);
		
		assertThat(leftPixelBefore).isEqualTo(leftPixelAfter);
		assertThat(rightPixelBefore).isNotEqualTo(rightPixelAfter);
	}
	
	private ImageDrawEntity createEntity() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(image, ImageType.JPEG);
	}
}
