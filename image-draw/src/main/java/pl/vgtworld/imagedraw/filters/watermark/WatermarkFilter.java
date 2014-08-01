package pl.vgtworld.imagedraw.filters.watermark;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public abstract class WatermarkFilter implements ImageDrawFilter {
	
	protected WatermarkFilter() {
	}
	
	public static class Builder {
		
		private WatermarkFilterType type;
		
		private ImageDrawEntity watermark;
		
		private WatermarkLocation location;
		
		private Integer margin;
		
		private int xOffset;
		
		private int yOffset;
		
		private int horizontalSpacing;
		
		private int verticalSpacing;
		
		public Builder setType(WatermarkFilterType type) {
			this.type = type;
			return this;
		}
		
		public Builder setWatermark(ImageDrawEntity watermark) {
			this.watermark = watermark;
			return this;
		}
		
		public Builder setLocation(WatermarkLocation location) {
			this.location = location;
			return this;
		}
		
		public Builder setMargin(int margin) {
			this.margin = margin;
			return this;
		}
		
		public Builder setXOffset(int xOffset) {
			this.xOffset = xOffset;
			return this;
		}
		
		public Builder setYOffset(int yOffset) {
			this.yOffset = yOffset;
			return this;
		}
		
		public Builder setHorizontalSpacing(int horizontalSpacing) {
			this.horizontalSpacing = horizontalSpacing;
			return this;
		}
		
		public Builder setVerticalSpacing(int verticalSpacing) {
			this.verticalSpacing = verticalSpacing;
			return this;
		}
		
		public WatermarkFilter build() {
			validateCommonData();
			switch (type) {
			case SINGLE:
				validateSingleVersion();
				return new WatermarkFilterSingle(watermark, location, margin);
			case REPEATED:
				validateRepeatedVersion();
				return new WatermarkFilterRepeated(watermark, xOffset, yOffset, horizontalSpacing, verticalSpacing);
			}
			throw new UnsupportedOperationException("Missing implementation for defined type.");
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
