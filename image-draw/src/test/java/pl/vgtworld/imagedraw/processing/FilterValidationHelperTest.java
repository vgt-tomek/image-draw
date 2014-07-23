package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class FilterValidationHelperTest {
	
	private FilterValidationHelper helper = new FilterValidationHelper();
	
	@Test
	public void shouldAcceptValidEntity() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 100, 100);
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldNotAcceptNullAsImage() throws IOException {
		helper.validateParameters(null, 0, 0, 100, 100);
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldNotAcceptEntityWithMissingImageData() throws IOException {
		ImageDrawEntity image = new ImageDrawEntity();
		
		helper.validateParameters(image, 0, 0, 100, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptLargeWidth() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 101, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptLargeHeight() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 100, 101);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeWidth() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, -10, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeHeight() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 100, -10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptZeroWidth() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 0, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptZeroHeight() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 0, 100, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeX() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, -5, 0, 100, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeY() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, -5, 100, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptLargeX() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 100, 0, 1, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptLargeY() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 100, 100, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeXPlusWidth() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 50, 0, 51, 100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptTooLargeYPlusHeight() throws IOException {
		ImageDrawEntity image = createValidImageEntity();
		
		helper.validateParameters(image, 0, 50, 100, 51);
	}
	
	private ImageDrawEntity createValidImageEntity() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(image, ImageType.JPEG);
	}
	
}
