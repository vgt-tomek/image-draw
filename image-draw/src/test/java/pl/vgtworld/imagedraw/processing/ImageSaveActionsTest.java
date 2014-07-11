package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageSaveActionsTest {
	
	private ImageSaveActions imageSaveActions = new ImageSaveActions(new ImageTypeMapper());
	
	@Test
	public void shouldCorrectlySaveImageToJpegFile() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		ImageDrawEntity image = new ImageDrawEntity(bufferedImage, ImageType.JPEG);
		
		File tempFile = createTemporaryFile();
		imageSaveActions.save(image, tempFile);
		
		assertThat(tempFile.exists()).isTrue();
	}
	
	@Test
	public void shouldCorrectlySaveImageToPngFile() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		ImageDrawEntity image = new ImageDrawEntity(bufferedImage, ImageType.PNG);
		
		File tempFile = createTemporaryFile();
		imageSaveActions.save(image, tempFile);
		
		assertThat(tempFile.exists()).isTrue();
	}
	
	@Test
	public void shouldCorrectlySaveImageToBmpFile() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		ImageDrawEntity image = new ImageDrawEntity(bufferedImage, ImageType.BMP);
		
		File tempFile = createTemporaryFile();
		imageSaveActions.save(image, tempFile);
		
		assertThat(tempFile.exists()).isTrue();
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionBecauseOfMissingImageType() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		ImageDrawEntity image = new ImageDrawEntity();
		image.setImage(bufferedImage);
		
		File tempFile = createTemporaryFile();
		imageSaveActions.save(image, tempFile);
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionBecauseOfMissingImageData() throws IOException {
		ImageDrawEntity image = new ImageDrawEntity();
		image.setImageType(ImageType.JPEG);
		
		File tempFile = createTemporaryFile();
		imageSaveActions.save(image, tempFile);
	}
	
	private File createTemporaryFile() throws IOException {
		File tempFile = File.createTempFile("test-prefix", ".jpg");
		tempFile.deleteOnExit();
		return tempFile;
	}
	
}
