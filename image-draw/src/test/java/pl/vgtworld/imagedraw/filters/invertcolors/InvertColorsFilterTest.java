package pl.vgtworld.imagedraw.filters.invertcolors;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class InvertColorsFilterTest {
	
	private static final int COLOR_MAX_VALUE = 255;
	
	@Test
	public void shouldInvertColorsOnImage() throws IOException {
		ImageDrawEntity image = loadImage();
		InvertColorsFilter filter = new InvertColorsFilter();
		
		Color upperLeftPixelBefore = new Color(image.getImage().getRGB(5, 5));
		Color lowerRightPixelBefore = new Color(image.getImage().getRGB(95, 95));
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color upperLeftPixelAfter = new Color(image.getImage().getRGB(5, 5));
		Color lowerRightPixelAfter = new Color(image.getImage().getRGB(95, 95));
		
		assertThat(upperLeftPixelBefore).isNotEqualTo(upperLeftPixelAfter);
		assertThat(upperLeftPixelBefore.getRed()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getRed());
		assertThat(upperLeftPixelBefore.getGreen()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getGreen());
		assertThat(upperLeftPixelBefore.getBlue()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getBlue());
		assertThat(lowerRightPixelBefore).isNotEqualTo(lowerRightPixelAfter);
		assertThat(lowerRightPixelBefore.getRed()).isEqualTo(COLOR_MAX_VALUE - lowerRightPixelAfter.getRed());
		assertThat(lowerRightPixelBefore.getGreen()).isEqualTo(COLOR_MAX_VALUE - lowerRightPixelAfter.getGreen());
		assertThat(lowerRightPixelBefore.getBlue()).isEqualTo(COLOR_MAX_VALUE - lowerRightPixelAfter.getBlue());
	}
	
	@Test
	public void shouldInvertColorsOnDefinedAreaOfImage() throws IOException {
		ImageDrawEntity image = loadImage();
		InvertColorsFilter filter = new InvertColorsFilter();
		
		Color upperLeftPixelBefore = new Color(image.getImage().getRGB(5, 5));
		Color lowerRightPixelBefore = new Color(image.getImage().getRGB(95, 95));
		filter.doFilter(image, 0, 0, 50, 50);
		Color upperLeftPixelAfter = new Color(image.getImage().getRGB(5, 5));
		Color lowerRightPixelAfter = new Color(image.getImage().getRGB(95, 95));
		
		assertThat(upperLeftPixelBefore).isNotEqualTo(upperLeftPixelAfter);
		assertThat(upperLeftPixelBefore.getRed()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getRed());
		assertThat(upperLeftPixelBefore.getGreen()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getGreen());
		assertThat(upperLeftPixelBefore.getBlue()).isEqualTo(COLOR_MAX_VALUE - upperLeftPixelAfter.getBlue());
		assertThat(lowerRightPixelBefore).isEqualTo(lowerRightPixelAfter);
	}
	
	private ImageDrawEntity loadImage() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(image, ImageType.JPEG);
	}
	
}
