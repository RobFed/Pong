
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.*;

public class ball {

	private ImageIcon imgBall;
	int xPos, yPos, width, height, xd, ud, rNum, raNum, x = 0;
	int direction;
	private boolean start;
	int speedx = 1;
	int speedy = 1;
	public final static int EAST = 0;
	public final static int WEST = 1;
	Random rnd = new Random();
	Rectangle2D br;

	public ball() {

		//imgBall = new ImageIcon("testball.png");
		//width = imgBall.getIconWidth();
		//height = imgBall.getIconHeight();
    width = height = 20;

		xPos = 235;
		yPos = 150;
		start = false;
		direction = EAST;

	}



	public int getX() {

		return xPos;
	}

	public int getY() {

		return yPos;
	}

	public void setX(int x) {

		xPos = x;
	}

	public void setY(int y) {

		yPos = y;
	}

	public Rectangle getRect() {

		return new Rectangle(xPos, yPos, width, height);
	}

	public void move() {

		if (x == 0) {
			rNum = rnd.nextInt(2);
			raNum = rnd.nextInt(2);
		}
		if (raNum == 0) {

			xPos += 10;
		} else {
			xPos -= 10;
		}
		x++;

		if (rNum == 0) {
			yPos += 10;
			if (yPos > 295 - height) {
				rNum = 1;
			}
		}

		else {
			yPos -= 10;

			if (yPos < 0) {
				rNum = 0;
			}
		}

		if (xPos <= 0) {
			x = 0;
		}

		if (xPos + width >= 480) {
			x = 0;
		}
	}

	public int getWidth() {

		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(Graphics2D g2) {

		//g2.drawImage(imgBall.getImage(), xPos, yPos, null);
    g2.setColor(Color.green);
    g2.fillOval(xPos, yPos, 20, 20);
	}


}
