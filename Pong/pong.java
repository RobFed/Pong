/*Robbie Fedorowicz
6/16/2017
The purpose of this program is to recreate the pong game.  It used multiple classes, as well as a key listener and window listener to create everything.
It requires two controls to be pressed to represent a two player game.

 */

//importing stuff
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

public class pong extends JPanel implements ActionListener, KeyListener,
		WindowListener {

	//declaring all required variables
	public final static int EAST = 0;
	public final static int WEST = 1;
	private final int WIDTH = 480, HEIGHT = 320;
	private ImageIcon imgBackground;
	private int xPos, yPos, direction, p1s = 0, p2s = 0;
	private Timer t, bTimer, p1Timer, p2Timer;
	private Random rnd;
	private ball b;
	private player1 p1;
	private player2 p2;
	private boolean upPressed = false, downPressed = false, wPressed = false,
			sPressed = false;
	private Rectangle2D br, paddle1, paddle2;
	private JLabel p1Score, p2Score;
	private String starting;


	public static void main(String[] args) {

		new pong();

	}

	public pong() {

		//creating the background
		imgBackground = new ImageIcon("pong_bg.png");

		//creating the frame
		setLayout(null);
		JFrame frame = new JFrame();
		frame.setContentPane(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("PONG");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(this);

		//creating timers and calling other classes
		t = new Timer(100, this);
		bTimer = new Timer(10, this);
    	p1Timer = new Timer(10, this);
    	p2Timer = new Timer(10, this);
		frame.addKeyListener(this);
		b = new ball();
		p1 = new player1();
		p2 = new player2();
		t.start();

		//creating the scores + starting screen
		p1Score = new JLabel();
		p1s = 0;
		String score1 = Integer.toString(p1s);
		p1Score.setText(score1);

		p2Score = new JLabel();
		p2s = 0;
		String score2 = Integer.toString(p2s);
		p2Score.setText(score2);

		starting = new String("Press SpaceBar to Start!");

		frame.add(p1Score);
		frame.add(p2Score);

		JOptionPane
				.showMessageDialog(
						null,
						"PLAYER ONE CONTROLS: W + S \nPLAYER TWO CONTROLS: UP + DOWN ARROW KEYS \nFirst player to 5 points wins!");

	}

	public void paintComponent(Graphics g) {

		//creating paint
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(imgBackground.getImage(), 0, 0, this);

		//drawing the score + starting message
		Font f = new Font("Serif", Font.BOLD, 20);
		g2.setFont(f);
		g2.setColor(Color.RED);
		g2.drawString("P1: " + p1s, 100, 20);
		g2.setColor(Color.CYAN);
		g2.drawString("P2: " + p2s, 350, 20);
		Color c = new Color(0, 255, 0);
		g2.setColor(c);
		g2.drawString(""+starting, 125, 200);

		//drawing paddles and ball
		if (p1 != null && p2 != null && b != null) {
			p1.draw(g2);
			p2.draw(g2);
			b.draw(g2);
		}

		//if the ball hits the left side of panel reset it and increase score
		if (b.xPos <= 0) {
			b.xPos = 235;
			b.yPos = 150;
			bTimer.stop();
			p2s++;
		}

		//if the ball hits the right side of panel reset it and increase score
		if (b.xPos + b.width >= 480) {
			b.xPos = 235;
			b.yPos = 150;
			bTimer.stop();
			p1s++;
		}

		//creating the rectangles for intersecting
		br = new Rectangle2D.Double(b.getX(), b.getY(), b.getWidth(),
				b.getHeight());
		paddle1 = new Rectangle2D.Double(p1.getX(), p1.getY(), p1.getWidth(),
				p1.getHeight());
		paddle2 = new Rectangle2D.Double(p2.getX(), p2.getY(), p2.getWidth(),
				p2.getHeight());

		//if the ball intersects with paddle 1, change x position
		if (br.intersects(paddle1)) {
			b.raNum = 0;

		}
		//if the ball intersects with paddle 2, change x position
		if (br.intersects(paddle2)) {
			b.raNum = 1;
		}
		//if player one scores 5 times
		if (p1s == 5) {
			t.stop();
			//output winning message
			int replay = JOptionPane.showConfirmDialog(null,
					"PLAYER ONE WINS! Play Again?", "WINNER",
					JOptionPane.YES_NO_OPTION);
			//if user doesnt want to replay close the program
			if (replay == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			//resets all information to restart the game
			starting = "Press SpaceBar to Start!";
			p1s = 0;
			p2s = 0;
			t.start();
		}
		//if player two scores 5 times
		if (p2s == 5) {
			t.stop();
			//output winning message
			int replay = JOptionPane.showConfirmDialog(null,
					"PLAYER TWO WINS! Play Again?", "WINNER",
					JOptionPane.YES_NO_OPTION);
			//if user doesnt want to replay close the program
			if (replay == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			//resets all information to restart game
			starting = "Press SpaceBar to Start!";
			p1s = 0;
			p2s = 0;
			t.start();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		//if player presses spacebar, start the game
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			bTimer.start();
			starting = "";
		}

		//if player one presses W, move up
		if (e.getKeyCode() == KeyEvent.VK_W || wPressed == true) {
      p1Timer.start();
      p1.moveUp();
      wPressed = true;

		}
		//if player 1 pressed S, move down
		if (e.getKeyCode() == KeyEvent.VK_S || sPressed == true) {
      p1Timer.start();
			p1.moveDown();
      sPressed = true;
		}
		//if player 2 pressed up, move up
		if (e.getKeyCode() == KeyEvent.VK_UP || upPressed == true) {
      p2Timer.start();
      upPressed = true;
			p2.moveUp();
		}
		//if player 2 pressed up, move up
		if (e.getKeyCode() == KeyEvent.VK_DOWN || downPressed == true) {
      p2Timer.start();
			p2.moveDown();
      downPressed = true;
		}
		//repaint
		repaint();

	}

	public void keyReleased(KeyEvent e) {

		//is user let goes of keys, turn that keys boolean to false
		if (e.getKeyCode() == KeyEvent.VK_W) {
      p1Timer.stop();
			wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
      p1Timer.stop();
			sPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
      p2Timer.stop();
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      p2Timer.stop();
			downPressed = false;
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bTimer) {
			b.move();
		}

		repaint();

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

		// IF USER TRIES CLOSING THE WINDOW, OUTPUTS THE "are you sure" MESSAGE
		int closeOption = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to exit?", "EXIT",
				JOptionPane.YES_NO_OPTION);

		// IF USER HITS YES, CLOSE WINDOW
		if (closeOption == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
