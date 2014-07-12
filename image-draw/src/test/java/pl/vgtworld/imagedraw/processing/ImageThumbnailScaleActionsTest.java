package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageThumbnailScaleActionsTest {
	
	@Test
	public void shouldFitThumbnailHorizontally() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createHDImage();
		
		thumbnailActions.thumbnail(image, 100, 100, null);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		
		assertThat(width).isEqualTo(100);
		assertThat(height).isEqualTo(55);
	}
	
	@Test
	public void shouldFitThumbnailVertically() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createHDImage();
		
		thumbnailActions.thumbnail(image, 100, 50, null);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		
		assertThat(width).isEqualTo(89);
		assertThat(height).isEqualTo(50);
	}
	
	@Test
	public void shouldFillHorizontalBarsWithBackgroundColor() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createHDImage();
		
		thumbnailActions.thumbnail(image, 100, 100, Color.YELLOW);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		Color topPixel = new Color(thumbnail.getRGB(50, 0));
		Color bottomPixel = new Color(thumbnail.getRGB(50, 99));
		
		assertThat(width).isEqualTo(100);
		assertThat(height).isEqualTo(100);
		assertThat(topPixel).isEqualTo(Color.YELLOW);
		assertThat(bottomPixel).isEqualTo(Color.YELLOW);
	}
	
	@Test
	public void shouldFillVerticalBarsWithBackgroundColor() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createHDImage();
		
		thumbnailActions.thumbnail(image, 100, 50, Color.YELLOW);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		Color leftPixel = new Color(thumbnail.getRGB(0, 25));
		Color rightPixel = new Color(thumbnail.getRGB(99, 25));
		
		assertThat(width).isEqualTo(100);
		assertThat(height).isEqualTo(50);
		assertThat(leftPixel).isEqualTo(Color.YELLOW);
		assertThat(rightPixel).isEqualTo(Color.YELLOW);
	}
	
	@Test
	public void shouldNotScaleUpSmallImage() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createSmallImage();
		
		thumbnailActions.thumbnail(image, 200, 200, null);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		
		assertThat(width).isEqualTo(100);
		assertThat(height).isEqualTo(100);
	}
	
	@Test
	public void shouldPlaceSmallImageInThumbnailCenter() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createSmallImage();
		
		thumbnailActions.thumbnail(image, 200, 200, Color.YELLOW);
		BufferedImage thumbnail = image.getImage();
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		Color topPixel = new Color(thumbnail.getRGB(100, 0));
		Color bottomPixel = new Color(thumbnail.getRGB(100, 199));
		Color leftPixel = new Color(thumbnail.getRGB(0, 100));
		Color rightPixel = new Color(thumbnail.getRGB(199, 100));
		
		assertThat(width).isEqualTo(200);
		assertThat(height).isEqualTo(200);
		assertThat(topPixel).isEqualTo(Color.YELLOW);
		assertThat(bottomPixel).isEqualTo(Color.YELLOW);
		assertThat(leftPixel).isEqualTo(Color.YELLOW);
		assertThat(rightPixel).isEqualTo(Color.YELLOW);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallWidth() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createSmallImage();
		
		thumbnailActions.thumbnail(image, 0, 100, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallHeight() throws IOException {
		ImageThumbnailScaleActions thumbnailActions = new ImageThumbnailScaleActions();
		ImageDrawEntity image = createSmallImage();
		
		thumbnailActions.thumbnail(image, 100, 0, null);
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
