package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageRotationActionsTest {
	
	@Test
	public void shouldProperlyRotate90DegreesClockwise() throws IOException {
		ImageRotationActions rotate = new ImageRotationActions();
		ImageDrawEntity image = createImage();
		
		rotate.rotate(image, 90);
		BufferedImage rotatedImage = image.getImage();
		Color topLeftPixel = new Color(rotatedImage.getRGB(0, 0));
		Color topRightPixel = new Color(rotatedImage.getRGB(99, 0));
		Color bottomLeftPixel = new Color(rotatedImage.getRGB(0, 299));
		Color bottomRightPixel = new Color(rotatedImage.getRGB(99, 299));
		
		assertThat(rotatedImage.getWidth()).isEqualTo(100);
		assertThat(rotatedImage.getHeight()).isEqualTo(300);
		assertThat(topLeftPixel).isEqualTo(Color.RED);
		assertThat(topRightPixel).isEqualTo(Color.BLACK);
		assertThat(bottomLeftPixel).isEqualTo(Color.RED);
		assertThat(bottomRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyRotate90DegreesCounterclockwise() throws IOException {
		ImageRotationActions rotate = new ImageRotationActions();
		ImageDrawEntity image = createImage();
		
		rotate.rotate(image, 270);
		BufferedImage rotatedImage = image.getImage();
		Color topLeftPixel = new Color(rotatedImage.getRGB(0, 0));
		Color topRightPixel = new Color(rotatedImage.getRGB(99, 0));
		Color bottomLeftPixel = new Color(rotatedImage.getRGB(0, 299));
		Color bottomRightPixel = new Color(rotatedImage.getRGB(99, 299));
		
		assertThat(rotatedImage.getWidth()).isEqualTo(100);
		assertThat(rotatedImage.getHeight()).isEqualTo(300);
		assertThat(topLeftPixel).isEqualTo(Color.RED);
		assertThat(topRightPixel).isEqualTo(Color.RED);
		assertThat(bottomLeftPixel).isEqualTo(Color.BLACK);
		assertThat(bottomRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyRotate180Degrees() throws IOException {
		ImageRotationActions rotate = new ImageRotationActions();
		ImageDrawEntity image = createImage();
		
		rotate.rotate(image, 180);
		BufferedImage rotatedImage = image.getImage();
		Color topLeftPixel = new Color(rotatedImage.getRGB(0, 0));
		Color topRightPixel = new Color(rotatedImage.getRGB(299, 0));
		Color bottomLeftPixel = new Color(rotatedImage.getRGB(0, 99));
		Color bottomRightPixel = new Color(rotatedImage.getRGB(299, 99));
		
		assertThat(rotatedImage.getWidth()).isEqualTo(300);
		assertThat(rotatedImage.getHeight()).isEqualTo(100);
		assertThat(topLeftPixel).isEqualTo(Color.RED);
		assertThat(topRightPixel).isEqualTo(Color.RED);
		assertThat(bottomLeftPixel).isEqualTo(Color.RED);
		assertThat(bottomRightPixel).isEqualTo(Color.BLACK);
	}
	
	private ImageDrawEntity createImage() throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream("/image-grid-300-100.png"));
		return new ImageDrawEntity(imageData, ImageType.PNG);
	}
	
}
