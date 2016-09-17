package pl.vgtworld.imagedraw.filters.edgedetect;

import org.junit.Test;
import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EdgeDetectFilterTest {

	@Test
	public void shouldSuccessfullyExecuteEdgeDetectFilter() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		EdgeDetectFilter filter = new EdgeDetectFilter();

		filter.doFilter(entity, 0, 0, 300, 100);
	}

	@Test
	public void shouldSuccessfullyExecuteEdgeDetectFilterWithCustomStrength() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		EdgeDetectFilter filter = new EdgeDetectFilter(2);

		filter.doFilter(entity, 0, 0, 300, 100);
	}

}