package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageOpenActionsTest {
	
	private ImageTypeMapper mapper = new ImageTypeMapper();
	
	@Test
	public void shouldOpenImageFromStream() throws IOException {
		InputStream stream = getClass().getResourceAsStream("/image-100-100.jpg");
		ImageOpenActions imageOpenActions = new ImageOpenActions(mapper);
		
		ImageDrawEntity image = imageOpenActions.open(stream);
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
	
	@Test
	public void shouldOpenImageFromFile() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		File file = new File(path);
		ImageOpenActions imageOpenActions = new ImageOpenActions(mapper);
		
		ImageDrawEntity image = imageOpenActions.open(file);
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
	
	@Test
	public void shouldProperlyRecognizeJpegImageType() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		File file = new File(path);
		ImageOpenActions imageOpenActions = new ImageOpenActions(mapper);
		
		ImageDrawEntity image = imageOpenActions.open(file);
		
		assertThat(image.getImageType()).isEqualTo(ImageType.JPEG);
	}
	
	@Test
	public void shouldProperlyRecognizeBmpImageType() throws IOException {
		String path = getClass().getResource("/image-100-100.bmp").getPath();
		File file = new File(path);
		ImageOpenActions imageOpenActions = new ImageOpenActions(mapper);
		
		ImageDrawEntity image = imageOpenActions.open(file);
		
		assertThat(image.getImageType()).isEqualTo(ImageType.BMP);
	}
	
}
