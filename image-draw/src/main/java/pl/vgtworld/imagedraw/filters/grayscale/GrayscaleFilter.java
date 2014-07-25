package pl.vgtworld.imagedraw.filters.grayscale;

import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public class GrayscaleFilter implements ImageDrawFilter {
	
	private static final int MAX_PIXEL_BRIGHTNESS = 255;
	
	private static final int RED_BIT_OFFSET = 16;
	
	private static final int GREEN_BIT_OFFSET = 8;
	
	private static final int COLOR_MASK = 0xFF;
	
	private float red;
	
	private float green;
	
	private float blue;
	
	public GrayscaleFilter() {
		red = 0.33f;
		green = 0.33f;
		blue = 0.33f;
	}
	
	public GrayscaleFilter(float red, float green, float blue) {
		this.red = red;
		this.blue = blue;
		this.green = green;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		BufferedImage imageData = image.getImage();
		for (int i = x; i < x + width; ++i) {
			for (int j = y; j < y + height; ++j) {
				int pixelBrightness = calculatePixelBrightness(imageData.getRGB(i, j));
				imageData.setRGB(i, j, convertBrightnessToColor(pixelBrightness));
			}
		}
	}
	
	private int convertBrightnessToColor(int brightness) {
		return brightness + (brightness << 8) + (brightness << 16);
	}
	
	private int calculatePixelBrightness(int rgb) {
		int pixelRed = (rgb >> RED_BIT_OFFSET) & COLOR_MASK;
		int pixelGreen = (rgb >> GREEN_BIT_OFFSET) & COLOR_MASK;
		int pixelBlue = rgb & COLOR_MASK;
		float brightness = pixelRed * red + pixelGreen * green + pixelBlue * blue;
		int roundedBrightness = (int) brightness;
		if (roundedBrightness > MAX_PIXEL_BRIGHTNESS) {
			return MAX_PIXEL_BRIGHTNESS;
		}
		return roundedBrightness;
	}
}
