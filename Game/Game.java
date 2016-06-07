package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.GameMenu;
import Replayer.Recorder;

//import Menu.KeyPressListener;

public class Game extends JPanel {

	public int mode = 0;
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	TexturePaint tp;
	TexturePaint flameTp;
	BufferedImage wood;
	BufferedImage flame;
	public static boolean startpressed;
	static boolean started;
	//public int serverMode = 999; // 999 - off, 1 -server, 0 - client

	public Ball ball;
	public Racquet racquet;
	public Racquet upperRacquet;
	public Block block;
	public ArrayList<Block> list;
	static boolean gameStarted;
	public static boolean paused = false;

	// Game gameObject = new Game();

	public static boolean isPaused() {
		return paused;
	}

	public static void setPaused(boolean paused) {
		Game.paused = paused;
	}

	public Game() {
		list = new ArrayList<Block>();
		for (int i = 0; i < 20; i++) {
			Block blk = new Block(true);
			block = blk;
			list.add(blk);
		}

		GameMenu.setCurrentGame(this);
		ball = new Ball();
		racquet = new Racquet(false);
		upperRacquet = new Racquet(true);
		
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				racquet.keyPressed(e); // nado li
				upperRacquet.keyPressed(e); // nado li
			}

			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				upperRacquet.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				upperRacquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	public void move() throws Throwable {
		// addKeyListener(new KeyPressListener());
		
		ball.move();
		racquet.move();
		upperRacquet.move();
		// list.get(1).move();
		for (int i = 0; i < 20; i++)
			list.get(i).move();
	}

	@Override
	public void paint(Graphics g) {
		try {
			wood = ImageIO.read(GameMenu.class.getClass().getResource("/wood.jpg"));
			flame = ImageIO.read(GameMenu.class.getClass().getResource("/flame.jpg"));
			backgroundImage = ImageIO.read(GameMenu.class.getClass().getResource("/back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tp = new TexturePaint(wood, new Rectangle(0, 0, 100, 100));
		flameTp = new TexturePaint(flame, new Rectangle(0, 0, 300, 300));
		// File file = new File("");
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(flameTp);
		ball.paint(g2d);
		g2d.setPaint(tp);
		racquet.paint(g2d);
		upperRacquet.paint(g2d);
		for (int i = 0; i < 20; i++)
			list.get(i).paint(g2d);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this);
	}

	@SuppressWarnings("deprecation")
	public void gameOver(boolean lowerWins) throws Throwable {
		Recorder.stopped = true;
		GameMenu.stopped = true;
		Object[] options = {"Главное меню" };
		int chose;
		if (lowerWins)
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 2", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		else {
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 1", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		}
		// System.out.println(chose);
		
		if (chose == 0) {
			if(GameMenu.mode == 3){
				GameMenu.getGameMenuJFrame().setVisible(false);
				GameMenu.netMenu.networkJFrame.setVisible(true);
			}
			//paused = true;
			//GameMenu.mode = 0;
			//GameMenu.recordingThread.stop();
			GameWrapper.getGameFrame().setVisible(false);
			GameMenu.getGameMenuJFrame().setVisible(true);
			//GameMenu.gameThread.stop();
			//GameMenu.recordingThread.stop();
			
			
		}
		else{
			
			//GameMenu.mode = 0;
			//GameMenu.recordingThread.stop();
			GameMenu.gameThread = new Thread(new Recorder());
			GameMenu.gameThread.start();
			block.placeBlocks();
			ball.setX(10);
			ball.setY(100);
			ball.xa = 1;
			ball.ya = 1;
			GameMenu.stopped = false;
			//GameMenu.recordingThread.stop();
			//GameWrapper.restartGame();
		}
		

		/*
		 * public Game getGameObject() { return gameObject; }
		 */

	}

}
