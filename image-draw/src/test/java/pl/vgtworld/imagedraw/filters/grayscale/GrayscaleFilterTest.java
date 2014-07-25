package pl.vgtworld.imagedraw.filters.grayscale;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class GrayscaleFilterTest {
	
	private static final String FILE_PHOTO = "/image-100-100.png";
	
	private static final String FILE_GRID = "/image-grid-300-100.png";
	
	private GrayscaleFilter filter = new GrayscaleFilter();
	
	@Test
	public void shouldConvertImageToGrayscale() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_PHOTO);
		
		filter.doFilter(image, 0, 0, 100, 100);
		Color pixel = new Color(image.getImage().getRGB(50, 50));
		
		assertThat(pixel.getRed()).isEqualTo(pixel.getBlue());
		assertThat(pixel.getRed()).isEqualTo(pixel.getGreen());
	}
	
	@Test
	public void shouldConvertPartOfImageToGrayscale() throws IOException {
		ImageDrawEntity image = loadImageEntity(FILE_PHOTO);
		
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
		
		filter.doFilter(image, 0, 0, 300, 100);
		Color pixel = new Color(image.getImage().getRGB(9, 0));
		
		assertThat(pixel.getRed()).isEqualTo(84);
		assertThat(pixel.getGreen()).isEqualTo(84);
		assertThat(pixel.getBlue()).isEqualTo(84);
	}
	
	private ImageDrawEntity loadImageEntity(String path) throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
		return new ImageDrawEntity(image, ImageType.PNG);
	}
	
}
