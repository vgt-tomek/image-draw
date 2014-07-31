package pl.vgtworld.imagedraw.filters.watermark;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;

public abstract class WatermarkFilter implements ImageDrawFilter {
	
	protected WatermarkFilter() {
	}
	
	public static class Builder {
		
		private ImageDrawEntity watermark;
		
		private WatermarkLocation location;
		
		private Integer margin;
		
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
		
		public WatermarkFilter build() {
			validate();
			return new WatermarkFilterSingle(watermark, location, margin);
		}
		
		private void validate() {
			if (watermark == null) {
				throw new IllegalStateException("Watermark is required.");
			}
			if (location == null) {
				throw new IllegalStateException("Location is required.");
			}
			validateMargin();
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
