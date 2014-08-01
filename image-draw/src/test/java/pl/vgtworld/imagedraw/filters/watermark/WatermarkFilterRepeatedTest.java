package pl.vgtworld.imagedraw.filters.watermark;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class WatermarkFilterRepeatedTest {
	
	private ImageDrawEntity image;
	
	private ImageDrawEntity watermark;
	
	@Before
	public void init() throws IOException {
		image = loadImage("/image-100-100.jpg");
		watermark = loadImage("/alpha-test.png");
	}
	
	@Test
	public void shouldFillImageWithWatermark() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, 0, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color firstRedPixel = new Color(result.getRGB(15, 5));
		Color secondRedPixel = new Color(result.getRGB(35, 5));
		Color thirdRedPixel = new Color(result.getRGB(15, 25));
		
		assertThat(firstRedPixel).isEqualTo(Color.RED);
		assertThat(secondRedPixel).isEqualTo(Color.RED);
		assertThat(thirdRedPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyNormalizeHorizontalNegativeOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, -60, 0, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(15, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 85));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyNormalizeHorizontalPositiveOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 60, 0, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(15, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 85));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyNormalizeVerticalNegativeOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, -60, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(15, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 85));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyNormalizeVerticalPositiveOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, 60, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(15, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 85));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyOffsetWatermarkHorizontallyWithNegativeOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, -10, 0, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(5, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 95));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldProperlyOffsetWatermarkHorizontallyWithPositiveOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 10, 0, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(5, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 95));
		
		assertThat(upperLeftPixel).isEqualTo(Color.RED);
		assertThat(lowerRightPixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldProperlyOffsetWatermarkVerticallyWithNegativeOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, -10, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(5, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 95));
		
		assertThat(upperLeftPixel).isEqualTo(Color.BLUE);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyOffsetWatermarkVerticallyWithPositiveOffset() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, 10, 0, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color upperLeftPixel = new Color(result.getRGB(5, 5));
		Color lowerRightPixel = new Color(result.getRGB(95, 95));
		
		assertThat(upperLeftPixel).isEqualTo(Color.BLUE);
		assertThat(lowerRightPixel).isEqualTo(Color.RED);
	}
	
	@Test
	public void shouldProperlyAddHorizontalSpacingBetweenWatermarks() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, 0, 10, 0);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color firstGreenPixel = new Color(result.getRGB(15, 15));
		Color spacingPixel = new Color(result.getRGB(25, 15));
		Color secondGreenPixel = new Color(result.getRGB(45, 15));
		
		assertThat(firstGreenPixel).isEqualTo(Color.GREEN);
		assertThat(secondGreenPixel).isEqualTo(Color.GREEN);
		assertThat(spacingPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
	}
	
	@Test
	public void shouldProperlyAddVerticalSpacingBetweenWatermarks() {
		WatermarkFilterRepeated filter = new WatermarkFilterRepeated(watermark, 0, 0, 0, 10);
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		BufferedImage result = image.getImage();
		Color firstGreenPixel = new Color(result.getRGB(15, 15));
		Color spacingPixel = new Color(result.getRGB(15, 25));
		Color secondGreenPixel = new Color(result.getRGB(15, 45));
		
		assertThat(firstGreenPixel).isEqualTo(Color.GREEN);
		assertThat(secondGreenPixel).isEqualTo(Color.GREEN);
		assertThat(spacingPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
	}
	
	private ImageDrawEntity loadImage(String path) throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream(path));
		return new ImageDrawEntity(imageData, ImageType.PNG);
	}
}
