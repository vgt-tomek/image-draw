package pl.vgtworld.imagedraw.filters.mosaic;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class MosaicFilterTest {
	
	@Test
	public void shouldCreateMosaicEffect() throws IOException {
		MosaicFilter filter = new MosaicFilter(10);
		ImageDrawEntity image = loadImage();
		
		filter.doFilter(image, 0, 0, image.getImage().getWidth(), image.getImage().getHeight());
		int upperLeftCellColor = image.getImage().getRGB(0, 0);
		
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				assertThat(image.getImage().getRGB(i, j)).isEqualTo(upperLeftCellColor);
			}
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptZeroAsSize() {
		new MosaicFilter(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeSize() {
		new MosaicFilter(-5);
	}
	
	private ImageDrawEntity loadImage() throws IOException {
		BufferedImage imageData = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		return new ImageDrawEntity(imageData, ImageType.JPEG);
	}
}
