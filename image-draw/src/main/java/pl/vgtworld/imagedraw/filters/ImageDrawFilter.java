package pl.vgtworld.imagedraw.filters;

import pl.vgtworld.imagedraw.ImageDrawEntity;

/**
 * Interface for filters used in
 * {@link pl.vgtworld.imagedraw.processing.ImageProcessing#applyFilter(ImageDrawFilter) ImageProcessing.applyFilter(filter)} method.
 */
@FunctionalInterface
public interface ImageDrawFilter {
	
	/**
	 * Applies filter to provided image and on defined area.
	 * 
	 * @param image Image, that should be processed by filter.
	 * @param x X ccordinate of the upper-left corner of the specified region.
	 * @param y Y ccordinate of the upper-left corner of the specified region.
	 * @param width Width of the specified region.
	 * @param height Height of the specified region.
	 */
	void doFilter(ImageDrawEntity image, int x, int y, int width, int height);
	
}
