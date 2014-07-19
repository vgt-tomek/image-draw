package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageProcessingTest {
	
	@Test
	public void shouldOpenImageFromPath() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		ImageProcessing imageProcessing = new ImageProcessing();
		
		imageProcessing.open(path);
		ImageDrawEntity image = imageProcessing.getImage();
		BufferedImage bufferedImage = image.getImage();
		
		assertThat(bufferedImage).isNotNull();
		assertThat(bufferedImage.getWidth()).isEqualTo(100);
		assertThat(bufferedImage.getHeight()).isEqualTo(100);
	}
	
	@Test
	public void shouldNotChangeOriginalImageTypeWhenSavingWithDifferentImageTypeUsingFile() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		ImageProcessing imageProcessing = new ImageProcessing();
		imageProcessing.open(path);
		
		File tempFile = File.createTempFile("iamge-draw-test", "");
		imageProcessing.save(tempFile, ImageType.PNG);
		
		assertThat(imageProcessing.getImage().getImageType()).isEqualTo(ImageType.JPEG);
	}
	
	@Test
	public void shouldNotChangeOriginalImageTypeWhenSavingWithDifferentImageTypeUsingPath() throws IOException {
		String path = getClass().getResource("/image-100-100.jpg").getPath();
		ImageProcessing imageProcessing = new ImageProcessing();
		imageProcessing.open(path);
		
		File tempFile = File.createTempFile("iamge-draw-test", "");
		imageProcessing.save(tempFile.getAbsolutePath(), ImageType.PNG);
		
		assertThat(imageProcessing.getImage().getImageType()).isEqualTo(ImageType.JPEG);
	}
	
}
