package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Menu.GameMenu;
import Network.Client;
import Network.ClientCommunicator;
import Network.ServerCommunicator;

public class Racquet {
	private int Y = 550;
	private int WIDTH = 100;
	private int HEIGHT = 15;
	int x = 130;
	int xa = 0;
	boolean firstPlayer;
	public static Game game;
	Client client;
	public ServerCommunicator sc;
	public ClientCommunicator cc;

	public Racquet(boolean upper) {

		if (upper)
			firstPlayer = false;
		else
			firstPlayer = true;
		if (upper)
			this.Y = 20;
		// g.setColor(Color.BLUE);

	}

	public void initRacquet() {
		game = GameMenu.getCurrentGame();
	}

	public void move() {
		if (getX() + xa > 0 && getX() + xa < game.getWidth() - WIDTH)
			setX(getX() + xa);
	}

	public void paint(Graphics2D g) {
		g.fillRoundRect(getX(), Y, WIDTH, HEIGHT, 8, 8);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;

	}

	public void keyPressed(KeyEvent e) {
		if (GameMenu.serverMode == 999) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT && firstPlayer) {
				xa = -1;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && firstPlayer)
				xa = 1;

			if (GameMenu.getCurrentGame().getClass() == GameVersusFriend.class) {
				if (e.getKeyCode() == KeyEvent.VK_A && !firstPlayer)
					xa = -1;
				if (e.getKeyCode() == KeyEvent.VK_D && !firstPlayer)
					xa = 1;
			}
		} else {
			if (this.Y == 20 && GameMenu.serverMode == 0) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					xa = -1;
					if (cc != null);
						//cc.sendToServer("-");
					//return;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					xa = 1;
					if (cc != null);
						//cc.sendToServer("+");
					//return;
				}
//				if (cc != null)
//					cc.sendToServer("0");
			}
			if (this.Y == 550 && GameMenu.serverMode == 1) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					xa = -1;
//					if (sc != null)
//						sc.sendToClient("-");
//					return;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					xa = 1;
//					if (sc != null)
//						sc.sendToClient("+");
//					return;
				}
//				if (sc != null)
//					sc.sendToClient("0");
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}