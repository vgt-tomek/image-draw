package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

public class ImageDrawActionsTest {
	
	@Test
	public void shouldDrawOneImageOnAnother() throws IOException {
		BufferedImage sourceImage = ImageIO.read(getClass().getResourceAsStream("/image-grid-300-100.png"));
		BufferedImage destinationImage = ImageIO.read(getClass().getResourceAsStream("/image-100-100.jpg"));
		ImageDrawEntity source = new ImageDrawEntity(sourceImage, ImageType.JPEG);
		ImageDrawEntity destination = new ImageDrawEntity(destinationImage, ImageType.JPEG);
		ImageDrawActions actions = new ImageDrawActions();
		
		actions.drawImage(source, destination, 10, 10);
		Color firstColumnPixel = new Color(destination.getImage().getRGB(19, 10));
		Color secondColumnPixel = new Color(destination.getImage().getRGB(29, 10));
		
		assertThat(firstColumnPixel).isEqualTo(Color.GREEN);
		assertThat(secondColumnPixel).isEqualTo(Color.RED);
	}
	
}
