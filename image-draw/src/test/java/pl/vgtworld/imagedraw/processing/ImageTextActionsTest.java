package pl.vgtworld.imagedraw.processing;

import org.junit.Test;
import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.ImageType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTextActionsTest {

	@Test
	public void shouldSuccessfullyDrawCenteredTextOnImage() throws IOException {
		ImageTextActions textActions = new ImageTextActions();

		textActions.drawText(
				createImageEntity(),
				"Lorem ipsum",
				new Font("SansSerif", Font.BOLD, 64),
				Color.BLACK,
				new Point(50, 50),
				TextHorizontalPosition.CENTER,
				TextVerticalPosition.MIDDLE
		);
	}

	@Test
	public void shouldSuccessfullyDrawTopLeftPositionedTextOnImage() throws IOException {
		ImageTextActions textActions = new ImageTextActions();

		textActions.drawText(
				createImageEntity(),
				"Lorem ipsum",
				new Font("SansSerif", Font.BOLD, 64),
				Color.BLACK,
				new Point(50, 50),
				TextHorizontalPosition.LEFT,
				TextVerticalPosition.TOP
		);
	}

	@Test
	public void shouldSuccessfullyDrawBottomRightPositionedTextOnImage() throws IOException {
		ImageTextActions textActions = new ImageTextActions();

		textActions.drawText(
				createImageEntity(),
				"Lorem ipsum",
				new Font("SansSerif", Font.BOLD, 64),
				Color.BLACK,
				new Point(50, 50),
				TextHorizontalPosition.RIGHT,
				TextVerticalPosition.BOTTOM
		);
	}

	private ImageDrawEntity createImageEntity() throws IOException {
		BufferedImage image = ImageIO.read(new File(getClass().getResource("/image-100-100.jpg").getPath()));
		return new ImageDrawEntity(image, ImageType.JPEG);
	}

}