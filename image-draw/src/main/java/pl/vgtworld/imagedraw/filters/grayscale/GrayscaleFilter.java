package pl.vgtworld.imagedraw.filters.grayscale;

import java.awt.image.BufferedImage;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

/**
 * Filter converting image to its grayscale version.
 */
public class GrayscaleFilter implements ImageDrawFilter {
	
	private static final float ONE_THIRD_CHANNEL_STRENGTH = 0.33f;
	
	private static final int MAX_PIXEL_BRIGHTNESS = 255;
	
	private static final int RED_BIT_OFFSET = 16;
	
	private static final int GREEN_BIT_OFFSET = 8;
	
	private static final int COLOR_MASK = 0xFF;
	
	private float red;
	
	private float green;
	
	private float blue;
	
	/**
	 * Creates filter with default configuration.
	 * 
	 * <p>
	 * Each RGB channel is used at the same strength, when converting image to
	 * grayscale.
	 */
	public GrayscaleFilter() {
		red = ONE_THIRD_CHANNEL_STRENGTH;
		green = ONE_THIRD_CHANNEL_STRENGTH;
		blue = ONE_THIRD_CHANNEL_STRENGTH;
	}
	
	/**
	 * Creates filter with custom configuration.
	 * 
	 * <p>
	 * Each RGB channel can have defined strength, when converting image to
	 * grayscale. Strength for each channel must be between 0 and 1.
	 * 
	 * @param red
	 *           Red channel strength during convertion.
	 * @param green
	 *           Green channel strength during convertion.
	 * @param blue
	 *           Blue channel strength during convertion.
	 */
	public GrayscaleFilter(float red, float green, float blue) {
		if (!validateChannelStrengthRange(red)
				|| !validateChannelStrengthRange(green)
				|| !validateChannelStrengthRange(blue)
				) {
			throw new IllegalArgumentException("Each channel value must be between 0 and 1.");
		}
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
	
	private boolean validateChannelStrengthRange(float strength) {
		if (strength < 0 || strength > 1) {
			return false;
		}
		return true;
	}
	
	private int convertBrightnessToColor(int brightness) {
		return brightness + (brightness << GREEN_BIT_OFFSET) + (brightness << RED_BIT_OFFSET);
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
