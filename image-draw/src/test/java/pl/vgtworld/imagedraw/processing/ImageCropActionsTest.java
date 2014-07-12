package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageCropActionsTest {
	
	@Test
	public void shouldProperlyCropImage() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 9, 19, 50, 25);
		BufferedImage croppedImage = image.getImage();
		Color firstTestPixel = new Color(croppedImage.getRGB(0, 0));
		Color secondTestPixel = new Color(croppedImage.getRGB(0, 1));
		
		assertThat(croppedImage.getWidth()).isEqualTo(50);
		assertThat(croppedImage.getHeight()).isEqualTo(25);
		assertThat(firstTestPixel).isEqualTo(Color.RED);
		assertThat(secondTestPixel).isEqualTo(Color.GREEN);
	}
	
	@Test
	public void shouldProperlyCropOnePixelWideColumnAtImageRightEdge() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 299, 0, 1, 100);
		BufferedImage croppedImage = image.getImage();
		Color testPixel = new Color(croppedImage.getRGB(0, 0));
		
		assertThat(croppedImage.getWidth()).isEqualTo(1);
		assertThat(croppedImage.getHeight()).isEqualTo(100);
		assertThat(testPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyCropOnePixelWideRowAtImageBottomEdge() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 0, 99, 300, 1);
		BufferedImage croppedImage = image.getImage();
		Color testPixel = new Color(croppedImage.getRGB(0, 0));
		
		assertThat(croppedImage.getWidth()).isEqualTo(300);
		assertThat(croppedImage.getHeight()).isEqualTo(1);
		assertThat(testPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyCropRightBottomPartOfImage() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 289, 89, 11, 11);
		BufferedImage croppedImage = image.getImage();
		Color topLeftPixel = new Color(croppedImage.getRGB(0, 0));
		Color bottomRightPixel = new Color(croppedImage.getRGB(10, 10));
		
		assertThat(croppedImage.getWidth()).isEqualTo(11);
		assertThat(croppedImage.getHeight()).isEqualTo(11);
		assertThat(topLeftPixel).isEqualTo(Color.GREEN);
		assertThat(bottomRightPixel).isEqualTo(Color.RED);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailCroppingRightBottomCornerWhenXIsOffByOne() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 290, 89, 11, 11);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailCroppingRightBottomCornerWhenYIsOffByOne() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 289, 90, 11, 11);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallX() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, -5, 20, 50, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeX() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 300, 20, 50, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallY() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 10, -2, 50, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeY() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 10, 100, 50, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallWidth() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 0, 0, 0, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeWidth() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 0, 0, 301, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooSmallHeight() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 0, 0, 50, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeHeight() throws IOException {
		ImageDrawEntity image = createImage();
		ImageCropActions cropActions = new ImageCropActions();
		
		cropActions.crop(image, 0, 0, 50, 101);
	}
	
	private ImageDrawEntity createImage() throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream("/image-grid-300-100.png"));
		return new ImageDrawEntity(imageData, ImageType.PNG);
	}
}
