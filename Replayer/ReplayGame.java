package Replayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Game.Block;
import Game.Game;
import Game.GameWrapper;
import Menu.GameMenu;
import Menu.ReplayMenu;

public class ReplayGame extends Game {

	public static Game game;
	String filename;
	BufferedReader reader;

	Point ball;
	Point upperRacquet;
	Point lowerRacquet;
	Point[] block = new Point[20];

	ReplayGame(String filepath) throws Throwable {
		super();
		this.filename = filepath;
		game = GameMenu.getCurrentGame();
		String a = new String();
		reader = null;
		File f = new File("saves/" + filename);
		try {
			reader = new BufferedReader(new FileReader(f));
			a = reader.readLine();

			int l = 0;
			int r = 0;
			for (int i = 0; i < 20; i++) {
				do {

					r++;
				} while (a.charAt(r) != ':');
				int first = Integer.parseInt(a.substring(l, r));
				l = r + 1;
				do {

					r++;
				} while (a.charAt(r) != '/');
				int second = Integer.parseInt(a.substring(l, r));
				l = r + 1;
				block[i] = new Point(first, second);
			}
			a = reader.readLine(); // читаем строку координат шара и ракеток
			l = 0;
			r = 0;
			do {
				r++;
			} while (a.charAt(r) != '/');
			int ballx = Integer.parseInt(a.substring(l, r));
			l = r + 1;
			do {
				r++;
			} while (a.charAt(r) != '/');
			int bally = Integer.parseInt(a.substring(l, r));
			l = r + 1;
			do {
				r++;
			} while (a.charAt(r) != '/');
			int urx = Integer.parseInt(a.substring(l, r));
			l = r + 1;
			do {
				r++;
			} while (a.charAt(r) != '/');
			int lrx = Integer.parseInt(a.substring(l, r));
			ball = new Point(ballx, bally);
			upperRacquet = new Point(urx, 20);
			lowerRacquet = new Point(lrx, 550);

			game.ball.setX(ball.getX());
			game.ball.setY(ball.getY());
			game.racquet.setX(lowerRacquet.getX());
			// game.racquet.setY(lowerRacquet.getY());
			game.upperRacquet.setX(upperRacquet.getX());
			for (int i = 0; i < 20; i++) {
				game.list.get(i).setX(block[i].getX());
				game.list.get(i).setY(block[i].getY());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("cvb");

		// TODO Auto-generated constructor stub
	}

	public int replayMove() throws InterruptedException {

		try {
			String a = reader.readLine();
			int slashes = 0;
			if (a == null)
				return 1;
			for (char c : a.toCharArray()) {
				if (c == '/')
					slashes++;
			}
			int[] coords = new int[slashes];

			int l = 0;
			int r = 0;
			for (int i = 0; i < slashes; i++) {
				do {
					r++;
				} while (a.charAt(r) != '/');
				coords[i] = Integer.parseInt(a.substring(l, r));
				l = r + 1;
			}
			game.ball.setX(coords[0]);
			game.ball.setY(coords[1]);
			game.upperRacquet.setX(coords[2]);
			game.racquet.setX(coords[3]);
			if (slashes > 4)
				GameMenu.currentGame.list.get(coords[4]).setX(1000);
			if (slashes > 5)
				GameMenu.currentGame.list.get(coords[5]).setX(1000);
			if (slashes > 6)
				GameMenu.currentGame.list.get(coords[6]).setX(1000);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void gameOver(boolean lowerWins) throws Throwable {

		Object[] options = { "Главное меню" };
		int chose;
		if (lowerWins)
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 2", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		else {
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 1", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		}

		if (chose == 0) {
			
			Replayer.replayFrame.setVisible(false);
			GameMenu.getGameMenuJFrame().setVisible(true);
			System.out.println("reached");
		}

	}

}

class Point {
	private int x;
	private int y;
	private boolean visible;

	Point(int posx, int posy) {
		this.x = posx;
		this.y = posy;
		this.visible = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
