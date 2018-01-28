import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;//swing timers are different from java.util.timers

import javafx.scene.shape.Rectangle;

public class Main extends JFrame implements ActionListener, KeyListener {

	private boolean play = false;
	private int totalbricks = 24;
	private int score = 0;
	private int delay = 30; // delay means that after every 30ms(or any other
							// unit),actionPerformed function will be called
	private Timer time = new Timer(delay, this);// for adding parameters in the
												// timer constructor ,we have to
												// import javax.swing.Timer
												// which is dirr form
												// java.util.import.Timer
	private int ballXpos = 120;
	private int ballYpos = 350;
	private int boardpos = 310;
	private int ballYdir = -2;
	private int ballXdir = -1;
	private Bricks br;

	Main() {
		br = new Bricks(4, 6);

		this.setBounds(10, 10, 700, 600); // parameters represent
											// x,y,width,height
											// x & y are coordinates of upper
											// left corner of rectangle
		this.setTitle("Brick Breaker");
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                            // if i click the
																// cross button
																// in the
																// frame,JFrame
																// gets disposed
																// off
		addKeyListener(this);
		setFocusable(true); // allows the component to gain focus of the keys
		setFocusTraversalKeysEnabled(false); // API introduced in released 1.4.
												// As of that release, the focus
												// subsystem consumes focus
												// traversal keys, such as Tab
												// and Shift Tab. If you need to
												// prevent the focus traversal
												// keys from being consumed, you
												// can call
												// component.setFocusTraversalKeysEnabled(false)
		time.start();

	}

	@Override
	public void paint(Graphics g) { // this func overrides paint function
		// background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		// borders
		g.setColor(Color.YELLOW);
		g.fillRect(2, 2, 6, 592);
		g.fillRect(2, 2, 692, 30);
		g.fillRect(691, 0, 6, 592);
		// board
		g.setColor(Color.GREEN);
		g.fillRect(boardpos, 550, 100, 8);
		// score
		g.setColor(Color.WHITE);
		Font font = new Font("Comic Sans MS", Font.BOLD, 25);
		g.setFont(font);
		g.drawString(" " + score, 570, 400);
		// ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballXpos, ballYpos, 20, 20);
		// bricks

		br.draw((Graphics2D) g);

		// disposing g
		g.dispose(); // You only need to dispose of Graphics when you actually
						// create it yourself (for example by calling
						// Component.getGraphics()).
						// In your case, you're not creating it, you're just
						// casting it, so do not call dispose in this case.

	}

	@Override
	public void actionPerformed(ActionEvent e) {// this method is called after
												// every delay(here,3ms)by the
												// timer

		if (play == true) {
			if (new Rectangle(ballXpos, ballYpos, 20, 20).intersects(boardpos, 550, 100, 8)) {
				ballYdir = -ballYdir;
			}
			if (ballYpos > 540) {
				play=false;
				int choice = JOptionPane.showConfirmDialog(this,"GAME OVER \nYour Score is " + score + " do u want to restart");

				if (choice == JOptionPane.YES_OPTION) {
					
					totalbricks = 24;
					score = 0;
					time.start();
					ballXpos = 120;
					ballYpos = 350;
					boardpos = 310;
					ballYdir = -2;
					ballXdir = -1;
					for (int i = 0; i < br.map.length; i++) {
						for (int j = 0; j < br.map[0].length; j++) {
							br.map[i][j] = 1;
						}
					}
					repaint();
				} else {
					super.dispose();
				}
			}
			A: for (int i = 0; i < br.map.length; i++) {
				for (int j = 0; j < br.map[0].length; j++) {
					if (br.map[i][j] > 0) {
						int brickX = j * (br.brickswidth + 10) + 80;
						int brickY = i * (br.bricksheight + 30) + 50;
						if (new Rectangle(ballXpos, ballYpos, 20, 20).intersects(brickX, brickY, br.brickswidth,br.bricksheight)) {
							br.map[i][j] = 0;
							score += 5;
							totalbricks--;
							if (totalbricks == 0) {
								repaint();
								play=false;
								int choice = JOptionPane.showConfirmDialog(this,"YOU WON \nYour Score is " + score + " do u want to restart");

								if (choice == JOptionPane.YES_OPTION) {
									
									totalbricks = 21;
									score = 0;
									time.start();
									ballXpos = 120;
									ballYpos = 350;
									boardpos = 310;
									ballYdir = -2;
									ballXdir = -1;
									for (int a = 0; a < br.map.length; a++) {
										for (int b = 0; b < br.map[0].length; b++) {
											br.map[a][b] = 1;
										}
									}
									repaint();
									break A;}
								else { super.dispose();}
								}
								if (ballXpos + 20 == brickX || ballXpos == brickX + br.brickswidth) {
									ballXdir = -ballXdir;
								} else {
									ballYdir = -ballYdir;
								}
								break A;
							}
						
					}
				}
				if (ballXpos <= 1) {
					ballXdir = -ballXdir;
				}
				if (ballYpos <= 10) {
					ballYdir = -ballYdir;
				}
				if (ballXpos >= 690) {
					ballXdir = -ballXdir;
				}

				ballXpos = ballXpos + ballXdir;
				ballYpos = ballYpos + ballYdir;

				repaint();

			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (boardpos >= 572) {
				boardpos = 592;
			} else {
				moveright();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (boardpos <= 21) {
				boardpos = 1;
			} else {
				moveleft();
			}
		}
	}

	public void moveright() {
		play = true;
		boardpos += 30;
	}

	public void moveleft() {
		play = true;
		boardpos -= 30;
	}

	@Override
	public void keyReleased(KeyEvent arg0) { // nthing to be happen when key is
												// released or typed
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
