package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

import pl.vgtworld.imagedraw.Image;

public class ImageProcessingTest {
	
	@Test
	public void shouldOpenImageFromPath() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		ImageProcessing id = new ImageProcessing();
		
		Image image = id.open(path);
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
	
}
