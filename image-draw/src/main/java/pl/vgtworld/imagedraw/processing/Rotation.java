package pl.vgtworld.imagedraw.processing;

public enum Rotation {
	
	QUARTER_CLOCKWISE(90), QOUARTER_COUNTERCLOCKWISE(270), HALF(180);
	
	private int degrees;
	
	int getDegrees() {
		return degrees;
	}
	
	private Rotation(int degrees) {
		this.degrees = degrees;
	}
	
}
