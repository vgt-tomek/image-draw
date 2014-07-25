package pl.vgtworld.imagedraw.filters.grayscale;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public class GrayscaleFilterTest {
	
	private static final String FILE_PHOTO = "/image-100-100.png";
	
	private static final String FILE_GRID = "/image-grid-300-100.png";
	
	@Test
	public void shouldConvertImageToGrayscale() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_PHOTO);
		GrayscaleFilter filter = new GrayscaleFilter();
		
		filter.doFilter(image, 0, 0, 100, 100);
		Color pixel = new Color(image.getImage().getRGB(50, 50));
		
		assertThat(pixel.getRed()).isEqualTo(pixel.getBlue());
		assertThat(pixel.getRed()).isEqualTo(pixel.getGreen());
	}
	
	@Test
	public void shouldConvertPartOfImageToGrayscale() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_PHOTO);
		GrayscaleFilter filter = new GrayscaleFilter();
		
		filter.doFilter(image, 50, 50, 50, 50);
		Color colorPixel = new Color(image.getImage().getRGB(20, 20));
		Color grayscalePixel = new Color(image.getImage().getRGB(75, 75));
		
		assertThat(colorPixel.getRed()).isNotEqualTo(colorPixel.getGreen());
		assertThat(colorPixel.getRed()).isNotEqualTo(colorPixel.getBlue());
		assertThat(colorPixel.getGreen()).isNotEqualTo(colorPixel.getBlue());
		assertThat(grayscalePixel.getRed()).isEqualTo(grayscalePixel.getGreen());
		assertThat(grayscalePixel.getRed()).isEqualTo(grayscalePixel.getBlue());
	}
	
	@Test
	public void shouldConvertGridToGrayscale() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_GRID);
		GrayscaleFilter filter = new GrayscaleFilter();
		
		filter.doFilter(image, 0, 0, 300, 100);
		Color pixel = new Color(image.getImage().getRGB(9, 0));
		
		assertThat(pixel.getRed()).isEqualTo(84);
		assertThat(pixel.getGreen()).isEqualTo(84);
		assertThat(pixel.getBlue()).isEqualTo(84);
	}
	
	@Test
	public void shouldConvertGridToGrayscaleUsingRedColorChannel() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_GRID);
		ImageDrawFilter filter = new GrayscaleFilter(1.0f, 0.0f, 0.0f);
		
		filter.doFilter(image, 0, 0, 300, 100);
		Color greenPixel = new Color(image.getImage().getRGB(9, 0));
		Color redPixel = new Color(image.getImage().getRGB(19, 0));
		
		assertThat(greenPixel.getRed()).isEqualTo(0);
		assertThat(greenPixel.getGreen()).isEqualTo(0);
		assertThat(greenPixel.getBlue()).isEqualTo(0);
		assertThat(redPixel.getRed()).isEqualTo(255);
		assertThat(redPixel.getGreen()).isEqualTo(255);
		assertThat(redPixel.getBlue()).isEqualTo(255);
	}
	
	@Test
	public void shouldConvertGridToGrayscaleUsingGreenColorChannel() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_GRID);
		ImageDrawFilter filter = new GrayscaleFilter(0.0f, 1.0f, 0.0f);
		
		filter.doFilter(image, 0, 0, 300, 100);
		Color greenPixel = new Color(image.getImage().getRGB(9, 0));
		Color redPixel = new Color(image.getImage().getRGB(19, 0));
		
		assertThat(greenPixel.getRed()).isEqualTo(255);
		assertThat(greenPixel.getGreen()).isEqualTo(255);
		assertThat(greenPixel.getBlue()).isEqualTo(255);
		assertThat(redPixel.getRed()).isEqualTo(0);
		assertThat(redPixel.getGreen()).isEqualTo(0);
		assertThat(redPixel.getBlue()).isEqualTo(0);
	}
	
	@Test
	public void shouldConvertGridToGrayscaleUsingBlueColorChannel() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_GRID);
		ImageDrawFilter filter = new GrayscaleFilter(0.0f, 0.0f, 1.0f);
		
		filter.doFilter(image, 0, 0, 300, 100);
		Color greenPixel = new Color(image.getImage().getRGB(9, 0));
		Color redPixel = new Color(image.getImage().getRGB(19, 0));
		
		assertThat(greenPixel.getRed()).isEqualTo(0);
		assertThat(greenPixel.getGreen()).isEqualTo(0);
		assertThat(greenPixel.getBlue()).isEqualTo(0);
		assertThat(redPixel.getRed()).isEqualTo(0);
		assertThat(redPixel.getGreen()).isEqualTo(0);
		assertThat(redPixel.getBlue()).isEqualTo(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithBelowRangeRedChannelValue() {
		new GrayscaleFilter(-0.1f, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithAboveRangeRedChannelValue() {
		new GrayscaleFilter(1.1f, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithBelowRangeGreenChannelValue() {
		new GrayscaleFilter(1, -0.1f, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithAboveRangeGreenChannelValue() {
		new GrayscaleFilter(1, 1.1f, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithBelowRangeBlueChannelValue() {
		new GrayscaleFilter(1, 1, -0.1f);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConstructingFilterWithAboveRangeBlueChannelValue() {
		new GrayscaleFilter(1, 1, 1.1f);
	}
	
	private ImageDrawEntity loadImageEntity(String path) throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
		return new ImageDrawEntity(image, ImageType.PNG);
	}
	
}
