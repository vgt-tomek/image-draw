package pl.vgtworld.imagedraw.filters.watermark;

/**
 * Allows to define, where to put watermark on image, when building filter using
 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilter.Builder watermark builder}.
 */
public enum WatermarkLocation {
	UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT, CENTER;
}
