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

public class WatermarkFilterSingleTest {
	
	private ImageDrawEntity image;
	
	private ImageDrawEntity watermark;
	
	@Before
	public void init() throws IOException {
		image = loadImage("/image-100-100.jpg");
		watermark = loadImage("/alpha-test.png");
	}
	
	@Test
	public void shouldPlaceWatermarkInUpperLeftCorner() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.UPPER_LEFT)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(5, 5));
		Color redPixel = new Color(image.getImage().getRGB(15, 5));
		Color greenPixel = new Color(image.getImage().getRGB(15, 15));
		Color bluePixel = new Color(image.getImage().getRGB(5, 15));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInUpperLeftCornerWithMargin() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.UPPER_LEFT)
				.setMargin(10)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(15, 15));
		Color redPixel = new Color(image.getImage().getRGB(25, 15));
		Color greenPixel = new Color(image.getImage().getRGB(25, 25));
		Color bluePixel = new Color(image.getImage().getRGB(15, 25));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInUpperRightCorner() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.UPPER_RIGHT)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(85, 5));
		Color redPixel = new Color(image.getImage().getRGB(95, 5));
		Color greenPixel = new Color(image.getImage().getRGB(95, 15));
		Color bluePixel = new Color(image.getImage().getRGB(85, 15));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInUpperRightCornerWithMargin() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.UPPER_RIGHT)
				.setMargin(10)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(75, 15));
		Color redPixel = new Color(image.getImage().getRGB(85, 15));
		Color greenPixel = new Color(image.getImage().getRGB(85, 25));
		Color bluePixel = new Color(image.getImage().getRGB(75, 25));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInLowerLeftCorner() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.LOWER_LEFT)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(5, 85));
		Color redPixel = new Color(image.getImage().getRGB(15, 85));
		Color greenPixel = new Color(image.getImage().getRGB(15, 95));
		Color bluePixel = new Color(image.getImage().getRGB(5, 95));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInLowerLeftCornerWithMargin() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.LOWER_LEFT)
				.setMargin(10)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(15, 75));
		Color redPixel = new Color(image.getImage().getRGB(25, 75));
		Color greenPixel = new Color(image.getImage().getRGB(25, 85));
		Color bluePixel = new Color(image.getImage().getRGB(15, 85));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInLowerRightCorner() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.LOWER_RIGHT)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(85, 85));
		Color redPixel = new Color(image.getImage().getRGB(95, 85));
		Color greenPixel = new Color(image.getImage().getRGB(95, 95));
		Color bluePixel = new Color(image.getImage().getRGB(85, 95));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInLowerRightCornerWithMargin() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.LOWER_RIGHT)
				.setMargin(10)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(75, 75));
		Color redPixel = new Color(image.getImage().getRGB(85, 75));
		Color greenPixel = new Color(image.getImage().getRGB(85, 85));
		Color bluePixel = new Color(image.getImage().getRGB(75, 85));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	@Test
	public void shouldPlaceWatermarkInCenter() {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(watermark)
				.setLocation(WatermarkLocation.CENTER)
				.build();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		Color transparentPixel = new Color(image.getImage().getRGB(45, 45));
		Color redPixel = new Color(image.getImage().getRGB(55, 45));
		Color greenPixel = new Color(image.getImage().getRGB(55, 55));
		Color bluePixel = new Color(image.getImage().getRGB(45, 55));
		
		assertThat(transparentPixel).isNotIn(Color.RED, Color.GREEN, Color.BLUE);
		assertThat(redPixel).isEqualTo(Color.RED);
		assertThat(greenPixel).isEqualTo(Color.GREEN);
		assertThat(bluePixel).isEqualTo(Color.BLUE);
	}
	
	private ImageDrawEntity loadImage(String path) throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream(path));
		return new ImageDrawEntity(imageData, ImageType.JPEG);
	}
}
