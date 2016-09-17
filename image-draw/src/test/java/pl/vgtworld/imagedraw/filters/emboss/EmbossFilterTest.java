package pl.vgtworld.imagedraw.filters.emboss;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EmbossFilterTest {

	@Rule
	public ExpectedException missingDirectionException = ExpectedException.none();

	@Test
	public void shouldSuccessfullyExecuteEmbossFilter() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		EmbossFilter filter = new EmbossFilter(EmbossDirection.LOWER_LEFT);

		filter.doFilter(entity, 0, 0, 300, 100);
	}

	@Test
	public void shouldSuccessfullyExecuteEmbossFilterWithStrengthDefined() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		EmbossFilter filter = new EmbossFilter(EmbossDirection.LOWER_RIGHT, 2);

		filter.doFilter(entity, 0, 0, 300, 100);
	}


	@Test
	public void shouldThrowExceptionWHenDirectionIsNotProvidewd() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		ImageDrawEntity entity = new ImageDrawEntity(image, ImageType.PNG);
		EmbossFilter filter = new EmbossFilter(null);

		missingDirectionException.expect(IllegalStateException.class);
		missingDirectionException.expectMessage("Direction is not defined.");
		filter.doFilter(entity, 0, 0, 300, 100);

	}

}