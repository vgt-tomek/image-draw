package pl.vgtworld.imagedraw.filters.sharpen;

import org.junit.Test;
import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SharpenFilterTest {

	@Test
	public void shouldSuccessfullyExecuteSharpenFilter() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		SharpenFilter filter = new SharpenFilter();

		filter.doFilter(entity, 0, 0, 300, 100);
	}

	@Test
	public void shouldSuccessfullyExecuteSharpenFilterWithCustomStrength() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		SharpenFilter filter = new SharpenFilter(2);

		filter.doFilter(entity, 0, 0, 300, 100);
	}

}