package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageThumbnailCropActionsTest {
	
	private ImageThumbnailCropActions thumbnailCrop = new ImageThumbnailCropActions();
	
	@Test
	public void shouldProperlyCropHorizontalThumbnail() throws IOException {
		ImageDrawEntity image = createHDImage();
		
		thumbnailCrop.thumbnail(image, 200, 100);
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		assertThat(width).isEqualTo(200);
		assertThat(height).isEqualTo(100);
	}
	
	@Test
	public void shouldProperlyCropVerticalThumbnail() throws IOException {
		ImageDrawEntity image = createHDImage();
		
		thumbnailCrop.thumbnail(image, 100, 200);
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		assertThat(width).isEqualTo(100);
		assertThat(height).isEqualTo(200);
	}
	
	@Test
	public void shouldProperlyScaleUpToThumbnailWidth() throws IOException {
		ImageDrawEntity image = createSmallImage();
		
		thumbnailCrop.thumbnail(image, 150, 80);
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		assertThat(width).isEqualTo(150);
		assertThat(height).isEqualTo(80);
	}
	
	@Test
	public void shouldProperlyScaleUpToThumbnailHeight() throws IOException {
		ImageDrawEntity image = createSmallImage();
		
		thumbnailCrop.thumbnail(image, 80, 150);
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		assertThat(width).isEqualTo(80);
		assertThat(height).isEqualTo(150);
	}
	
	@Test
	public void shouldProperlyScaleUpToThumbnailSize() throws IOException {
		ImageDrawEntity image = createSmallImage();
		
		thumbnailCrop.thumbnail(image, 150, 150);
		BufferedImage bufferedImage = image.getImage();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		assertThat(width).isEqualTo(150);
		assertThat(height).isEqualTo(150);
	}
	
	private ImageDrawEntity createHDImage() throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream("/image-1920-1080.jpg"));
		return new ImageDrawEntity(imageData, ImageType.PNG);
	}
	
	private ImageDrawEntity createSmallImage() throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(imageData, ImageType.PNG);
	}
	
}
