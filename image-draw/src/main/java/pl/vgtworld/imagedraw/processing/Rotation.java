package pl.vgtworld.imagedraw.processing;

/**
 * Allows to define rotation angle, when rotating image using
 * {@link pl.vgtworld.imagedraw.processing.ImageProcessing#rotate(Rotation) rotate} method.
 */
public enum Rotation {
	
	QUARTER_CLOCKWISE(90), QOUARTER_COUNTERCLOCKWISE(270), HALF(180);
	
	private int degrees;

	Rotation(int degrees) {
		this.degrees = degrees;
	}

	int getDegrees() {
		return degrees;
	}

}
