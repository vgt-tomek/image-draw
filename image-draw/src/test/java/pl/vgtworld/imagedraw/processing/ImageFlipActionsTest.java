package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageFlipActionsTest {
	
	private ImageFlipActions flipActions = new ImageFlipActions();
	
	@Test
	public void shouldFlipImageHorizontally() throws IOException {
		ImageDrawEntity image = createImage();
		
		int upperLeftPixelBefore = image.getImage().getRGB(0, 0);
		int lowerLeftPixelBefore = image.getImage().getRGB(0, 99);
		flipActions.flipHorizontal(image);
		int upperLeftPixelAfter = image.getImage().getRGB(0, 0);
		int upperRightPixelAfter = image.getImage().getRGB(99, 0);
		int lowerLeftPixelAfter = image.getImage().getRGB(0, 99);
		int lowerRightPixelAfter = image.getImage().getRGB(99, 99);
		
		assertThat(upperLeftPixelBefore).isNotEqualTo(upperLeftPixelAfter);
		assertThat(upperLeftPixelBefore).isEqualTo(upperRightPixelAfter);
		assertThat(lowerLeftPixelBefore).isNotEqualTo(lowerLeftPixelAfter);
		assertThat(lowerLeftPixelBefore).isEqualTo(lowerRightPixelAfter);
	}
	
	@Test
	public void shouldFlipImageVertically() throws IOException {
		ImageDrawEntity image = createImage();
		
		int upperLeftPixelBefore = image.getImage().getRGB(0, 0);
		int upperRightPixelBefore = image.getImage().getRGB(99, 0);
		flipActions.flipVertical(image);
		int upperLeftPixelAfter = image.getImage().getRGB(0, 0);
		int upperRightPixelAfter = image.getImage().getRGB(99, 0);
		int lowerLeftPixelAfter = image.getImage().getRGB(0, 99);
		int lowerRightPixelAfter = image.getImage().getRGB(99, 99);
		
		assertThat(upperLeftPixelBefore).isNotEqualTo(upperLeftPixelAfter);
		assertThat(upperLeftPixelBefore).isEqualTo(lowerLeftPixelAfter);
		assertThat(upperRightPixelBefore).isNotEqualTo(upperRightPixelAfter);
		assertThat(upperRightPixelBefore).isEqualTo(lowerRightPixelAfter);
	}
	
	private ImageDrawEntity createImage() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(image, ImageType.JPEG);
	}
}
