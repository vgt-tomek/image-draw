package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import pl.vgtworld.imagedraw.Image;

public class ImageProcessingTest {
	
	@Test
	public void shouldOpenImageFromStream() throws IOException {
		InputStream stream = getClass().getResourceAsStream("/image-100-100.jpg");
		ImageProcessing id = new ImageProcessing();
		
		Image image = id.open(stream);
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
	
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
	
	@Test
	public void shouldOpenImageFromFile() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		File file = new File(path);
		ImageProcessing id = new ImageProcessing();
		
		Image image = id.open(file);
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
}
