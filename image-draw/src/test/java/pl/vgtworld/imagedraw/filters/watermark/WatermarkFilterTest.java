package pl.vgtworld.imagedraw.filters.watermark;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class WatermarkFilterTest {
	
	@Test
	public void shouldBuildWatermarkFilterSingleVersion() throws IOException {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(loadWatermark())
				.setMargin(10)
				.setLocation(WatermarkLocation.UPPER_LEFT)
				.build();
		
		assertThat(filter).isNotNull();
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldFailBecauseOfMissingWatermark() {
		new WatermarkFilter.Builder()
				.setMargin(10)
				.setLocation(WatermarkLocation.UPPER_LEFT)
				.build();
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldFailBecauseOfMissingLocation() throws IOException {
		new WatermarkFilter.Builder()
				.setWatermark(loadWatermark())
				.setMargin(10)
				.build();
	}
	
	@Test
	public void shouldBuildFilterWhenMarginIsMissing() throws IOException {
		WatermarkFilter filter = new WatermarkFilter.Builder()
				.setWatermark(loadWatermark())
				.setLocation(WatermarkLocation.UPPER_LEFT)
				.build();
		
		assertThat(filter).isNotNull();
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldFailBecauseOfUsingMarginWithCenterLocation() throws IOException {
		new WatermarkFilter.Builder()
				.setWatermark(loadWatermark())
				.setLocation(WatermarkLocation.CENTER)
				.setMargin(10)
				.build();
	}
	
	private ImageDrawEntity loadWatermark() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/alpha-test.png"));
		return new ImageDrawEntity(bufferedImage, ImageType.JPEG);
	}
}
