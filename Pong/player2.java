
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

public class player2 {
	private ImageIcon racket2;
	int xPos, yPos, width, height, yVel, xVel;
	Rectangle2D paddle2;

	public player2() {



    width = 10;
    height = 80;
		xPos = 455;

	}

	public int getX() {

		return xPos;
	}

	public int getY() {

		return yPos;
	}

	public int getWidth() {

		return width;
	}

	public int getHeight() {

		return height;
	}

	public void setX(int x) {

		xPos = x;
	}

	public void setY(int y) {

		yPos = y;
	}

  public void moveUp() {
    if (yVel < 0)
      yVel = 0;
    yVel+=2;
		yPos -= yVel;
    if (yVel >= 10)
      yVel = 10;
		if (yPos < 0) {

			yPos = 0;
		}

	}

	public void moveDown() {
    if (yVel > 0)
      yVel = 0;
    yVel-=2;
		yPos -= yVel;
    if (yVel <= -10)
      yVel = -10;
		if (yPos + height > 300) {
			yPos = 300-height;
		}
	}

	public Rectangle getRect() {

		return new Rectangle(xPos, yPos, width, height);
	}

	public void draw(Graphics2D g2) {

    g2.setColor(Color.CYAN);

    g2.fillRect(xPos,yPos,width,height);

	}

}
