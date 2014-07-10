package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.Image;
import pl.vgtworld.imagedraw.ImageType;

public class ImageResizeActionsTest {
	
	@Test(expected = IllegalStateException.class)
	public void shouldNotAllowNullAsBothDimensions() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowZeroWidth() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowNegativeWidth() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, -10, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowZeroHeight() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, null, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowNegativeHeight() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, null, -10);
	}
	
	@Test
	public void shouldCalculateHeightWhenScalingDown() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 60, null);
		int newHeight = testImage.getImage().getHeight();
		
		assertThat(newHeight).isEqualTo(20);
	}
	
	@Test
	public void shouldCalculateHeightWhenScalingUp() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 600, null);
		int newHeight = testImage.getImage().getHeight();
		
		assertThat(newHeight).isEqualTo(200);
	}
	
	@Test
	public void shouldRoundDownHeightFraction() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 298, null);
		int newHeight = testImage.getImage().getHeight();
		
		assertThat(newHeight).isEqualTo(99);
	}
	
	@Test
	public void shouldRoundUpHeightFraction() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 299, null);
		int newHeight = testImage.getImage().getHeight();
		
		assertThat(newHeight).isEqualTo(100);
	}
	
	@Test
	public void shouldFallbackToOneWhenCalculatedRoundedHeightIsZero() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openHorizontalTestImage();
		
		resizeActions.resize(testImage, 1, null);
		int newHeight = testImage.getImage().getHeight();
		
		assertThat(newHeight).isEqualTo(1);
	}
	
	@Test
	public void shouldCalculateWidthWhenScalingDown() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openVerticalTestImage();
		
		resizeActions.resize(testImage, null, 60);
		int newWidth = testImage.getImage().getWidth();
		
		assertThat(newWidth).isEqualTo(20);
	}
	
	@Test
	public void shouldCalculateWidthWhenScalingUp() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openVerticalTestImage();
		
		resizeActions.resize(testImage, null, 600);
		int newWidth = testImage.getImage().getWidth();
		
		assertThat(newWidth).isEqualTo(200);
	}
	
	@Test
	public void shouldRoundDownWidthFraction() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openVerticalTestImage();
		
		resizeActions.resize(testImage, null, 298);
		int newWidth = testImage.getImage().getWidth();
		
		assertThat(newWidth).isEqualTo(99);
	}
	
	@Test
	public void shouldRoundUpWidthFraction() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openVerticalTestImage();
		
		resizeActions.resize(testImage, null, 299);
		int newWidth = testImage.getImage().getWidth();
		
		assertThat(newWidth).isEqualTo(100);
	}
	
	@Test
	public void shouldFallbackToOneWhenCalculatedRoundedWidthIsZero() throws IOException {
		ImageResizeActions resizeActions = new ImageResizeActions();
		Image testImage = openVerticalTestImage();
		
		resizeActions.resize(testImage, null, 1);
		int newWidth = testImage.getImage().getWidth();
		
		assertThat(newWidth).isEqualTo(1);
	}
	
	private Image openHorizontalTestImage() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-300-100.jpg"));
		return new Image(image, ImageType.JPEG);
	}
	
	private Image openVerticalTestImage() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-100-300.jpg"));
		return new Image(image, ImageType.JPEG);
	}
	
}
