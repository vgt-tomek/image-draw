package pl.vgtworld.imagedraw.filters.watermark;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

/**
 * Filter applying watermark on image.
 */
public abstract class WatermarkFilter implements ImageDrawFilter {
	
	protected WatermarkFilter() {
	}
	
	/**
	 * Builder class used for creating watermark filter with custom configuration.
	 */
	public static class Builder {
		
		private WatermarkFilterType type;
		
		private ImageDrawEntity watermark;
		
		private WatermarkLocation location;
		
		private Integer margin;
		
		private int xOffset;
		
		private int yOffset;
		
		private int horizontalSpacing;
		
		private int verticalSpacing;
		
		/**
		 * Allows to set type of created filter
		 * .
		 * @param type Filter type.
		 * @return Builder instance.
		 */
		public Builder setType(WatermarkFilterType type) {
			this.type = type;
			return this;
		}
		
		/**
		 * Allows to set image, that will be used by filter as watermark.
		 * 
		 * @param watermark Watermark image.
		 * @return Builder instance.
		 */
		public Builder setWatermark(ImageDrawEntity watermark) {
			this.watermark = watermark;
			return this;
		}
		
		/**
		 * Allows to set location, where watermark will be rendered on image.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#SINGLE WatermarkFilterType.SINGLE}
		 * filter type.
		 * 
		 * @param location Watermark location.
		 * @return Builder instance.
		 */
		public Builder setLocation(WatermarkLocation location) {
			this.location = location;
			return this;
		}
		
		/**
		 * Allows to set distance from edge of image, when placing watermark.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#SINGLE WatermarkFilterType.SINGLE}
		 * filter type and location other than
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkLocation#CENTER WatermarkLocation.CENTER}. 
		 * 
		 * @param margin Distance from image edge in pixels.
		 * @return Builder instance.
		 */
		public Builder setMargin(int margin) {
			this.margin = margin;
			return this;
		}
		
		/**
		 * Allows to set starting horizontal offset of watermark placement from upper-left corner of image.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#REPEATED WatermarkFilterType.REPEATED}
		 * filter type.
		 * 
		 * @param xOffset Horizontal offset from upper-left corner of image.
		 * @return Builder instance.
		 */
		public Builder setXOffset(int xOffset) {
			this.xOffset = xOffset;
			return this;
		}
		
		/**
		 * Allows to set starting vertical offset of watermark placement from upper-left corner of image.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#REPEATED WatermarkFilterType.REPEATED}
		 * filter type.
		 * 
		 * @param yOffset Vertical offset from upper-left corner of image.
		 * @return Builder instance.
		 */
		public Builder setYOffset(int yOffset) {
			this.yOffset = yOffset;
			return this;
		}
		
		/**
		 * Allows to set horizontal spacing between rendered watermarks.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#REPEATED WatermarkFilterType.REPEATED}
		 * filter type.
		 * 
		 * @param horizontalSpacing Horizontal spacing between rendered watermarks.
		 * @return Builder instance.
		 */
		public Builder setHorizontalSpacing(int horizontalSpacing) {
			this.horizontalSpacing = horizontalSpacing;
			return this;
		}
		
		/**
		 * Allows to set vertical spacing between rendered watermarks.
		 * 
		 * <p>
		 * This option can be used only with
		 * {@link pl.vgtworld.imagedraw.filters.watermark.WatermarkFilterType#REPEATED WatermarkFilterType.REPEATED}
		 * filter type.
		 * 
		 * @param verticalSpacing Vertical spacing between rendered watermarks.
		 * @return Builder instance.
		 */
		public Builder setVerticalSpacing(int verticalSpacing) {
			this.verticalSpacing = verticalSpacing;
			return this;
		}
		
		/**
		 * Creates filter object based on defined configuration.
		 * 
		 * @return Filter object.
		 */
		public WatermarkFilter build() {
			validateCommonData();
			switch (type) {
				case SINGLE:
					validateSingleVersion();
					return new WatermarkFilterSingle(watermark, location, margin);
				case REPEATED:
					validateRepeatedVersion();
					return new WatermarkFilterRepeated(watermark, xOffset, yOffset, horizontalSpacing, verticalSpacing);
				default:
					throw new UnsupportedOperationException("Missing implementation for defined type.");
			}
		}
		
		private void validateCommonData() {
			if (type == null) {
				throw new IllegalStateException("Type is required");
			}
			if (watermark == null) {
				throw new IllegalStateException("Watermark is required.");
			}
		}
		
		private void validateSingleVersion() {
			if (location == null) {
				throw new IllegalStateException("Location is required.");
			}
			if (xOffset != 0 || yOffset != 0) {
				throw new IllegalStateException("Offset parameters are used with repeated filter version.");
			}
			if (horizontalSpacing != 0 || verticalSpacing != 0) {
				throw new IllegalStateException("Spacing parameters are used with repeated filter version.");
			}
			validateMargin();
		}
		
		private void validateRepeatedVersion() {
			if (horizontalSpacing < 0) {
				throw new IllegalStateException("Horizontal spacing must be greater than or equal to 0.");
			}
			if (verticalSpacing < 0) {
				throw new IllegalStateException("Vertical spacing must be greater than or equal to 0.");
			}
			if (location != null) {
				throw new IllegalStateException("Location is used with single filter version.");
			}
			if (margin != null) {
				throw new IllegalStateException("Margin is used with single filter version.");
			}
		}

		private void validateMargin() {
			if (margin != null && margin < 0) {
				throw new IllegalStateException("Margin must be greater or equal to 0.");
			}
			if (margin != null && location == WatermarkLocation.CENTER) {
				throw new IllegalStateException("Margin should not be used, when location is set to CENTER.");
			}
			if (margin == null) {
				margin = 0;
			}
		}
		
	}
}
