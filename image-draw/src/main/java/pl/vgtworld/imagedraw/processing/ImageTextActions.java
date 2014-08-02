package pl.vgtworld.imagedraw.processing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pl.vgtworld.imagedraw.ImageDrawEntity;

class ImageTextActions {
	
	void drawText(ImageDrawEntity image, String text, Font font, Color color,
			int x, int y, TextHorizontalPosition horizontalPositioning, TextVerticalPosition verticalPositioning) {
		Graphics2D graphics = image.getImage().createGraphics();
		Rectangle textBounds = graphics.getFontMetrics(font).getStringBounds(text, graphics).getBounds();
		int finalX = calculateFinalXPosition(x, horizontalPositioning, textBounds);
		int finalY = calculateFinalYPosition(y, verticalPositioning, textBounds);
		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawString(text, finalX, finalY);
		graphics.dispose();
	}
	
	private int calculateFinalXPosition(int x, TextHorizontalPosition horizontalPositioning, Rectangle textBounds) {
		int finalX = 0;
		switch (horizontalPositioning) {
		case LEFT:
			finalX = x;
			break;
		case CENTER:
			finalX = x - (textBounds.width / 2);
			break;
		case RIGHT:
			finalX = x - textBounds.width;
			break;
		}
		return finalX;
	}
	
	private int calculateFinalYPosition(int y, TextVerticalPosition verticalPositioning, Rectangle textBounds) {
		int finalY = 0;
		switch (verticalPositioning) {
		case TOP:
			finalY = y - textBounds.y;
			break;
		case MIDDLE:
			finalY = y - textBounds.y - textBounds.height / 2;
			break;
		case BASELINE:
			finalY = y;
			break;
		case BOTTOM:
			finalY = y - (textBounds.y + textBounds.height);
			break;
		}
		return finalY;
	}
	
}
