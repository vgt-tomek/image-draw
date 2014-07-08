package pl.vgtworld.imagedraw.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import pl.vgtworld.imagedraw.Image;

public class ImageProcessing {
	
	public Image open(String path) throws IOException {
		return open(new File(path));
	}
	
	public Image open(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		return new Image(bufferedImage);
	}
	
	public Image open(InputStream stream) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(stream);
		return new Image(bufferedImage);
	}
	
}
