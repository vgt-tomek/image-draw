package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageTextActions {

	void drawText(ImageDrawEntity image, String text, Font font, Color color,
			Point startingPoint, TextHorizontalPosition horizontalPositioning, TextVerticalPosition verticalPositioning) {
		Graphics2D graphics = image.getImage().createGraphics();
		Rectangle textBounds = graphics.getFontMetrics(font).getStringBounds(text, graphics).getBounds();
		int finalX = calculateFinalXPosition(startingPoint.x, horizontalPositioning, textBounds);
		int finalY = calculateFinalYPosition(startingPoint.y, verticalPositioning, textBounds);
		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawString(text, finalX, finalY);
		graphics.dispose();
	}

	private int calculateFinalXPosition(int x, TextHorizontalPosition horizontalPositioning, Rectangle textBounds) {
		switch (horizontalPositioning) {
			case LEFT:
				return x;
			case CENTER:
				return x - (textBounds.width / 2);
			case RIGHT:
				return x - textBounds.width;
			default:
				throw new UnsupportedOperationException("Unknown horizontal positioning value.");
		}
	}

	private int calculateFinalYPosition(int y, TextVerticalPosition verticalPositioning, Rectangle textBounds) {
		switch (verticalPositioning) {
			case TOP:
				return y - textBounds.y;
			case MIDDLE:
				return y - textBounds.y - textBounds.height / 2;
			case BASELINE:
				return y;
			case BOTTOM:
				return y - (textBounds.y + textBounds.height);
			default:
				throw new UnsupportedOperationException("Unknown vertical positioning value.");
		}
	}

}
