package pl.vgtworld.imagedraw.filters.gaussianblur;

import org.junit.Test;
import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GaussianBlurFilterTest {

	@Test
	public void shouldSuccessfullyExecuteGaussianBlurFilter() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		GaussianBlurFilter filter = new GaussianBlurFilter(2);

		filter.doFilter(entity, 0, 0, 300, 100);
	}

	@Test
	public void shouldSuccessfullyExecuteGaussianBlurFilterWithCustomConfiguration() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		GaussianBlurFilter filter = new GaussianBlurFilter(2, 2);

		filter.doFilter(entity, 0, 0, 300, 100);
	}

}