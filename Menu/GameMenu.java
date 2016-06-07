package Menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Game.Block;
import Game.Game;
import Game.GameVersusBot;
import Game.GameVersusFriend;
import Game.GameWrapper;
import Replayer.Recorder;

public class GameMenu extends JPanel implements Runnable {

	private volatile boolean running = true;
	public static JFrame GameMenuJFrame;
	public static int mode = 0;
	GameVersusBot gameVersusBot;
	private BufferedImage tennis;
	private TexturePaint texture;
	public static Thread recordingThread;
	public static Game currentGame;
	public static GameWrapper gameWrapper;
	public static volatile Thread gameThread;
	// public static boolean stopped = false;
	public static boolean stopped = false;
	public static Point point;
	public static NetworkMenu netMenu;
	public static int serverMode = 999;
	/**The main menu of the game*/
	public GameMenu() {
		int c;

	}
	/** Termination*/
	public void terminate() {
		running = false;
	}
	/** This function paint all objects to screen*/	
	@Override
	public void paint(Graphics g) {
		URL img1 = this.getClass().getResource("/tennisball.jpg");
		Toolkit tk = this.getToolkit();
		//tennis = (BufferedImage) tk.getImage(img1);
		try {
			tennis = ImageIO.read((ImageInputStream) tk.getImage(img1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		texture = new TexturePaint(tennis, new Rectangle(0, 0, 100, 100));

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// ball.paint(g2d);
		g2d.setPaint(texture);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("started right now");
		GameMenuJFrame = new JFrame();
		GameMenuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameMenuJFrame.setResizable(false);
		GameMenuJFrame.setLayout(null);
		GameMenuJFrame.setSize(640, 360);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		GameMenuJFrame.setLocation(dim.width / 2 - GameMenuJFrame.getSize().width / 2,
				dim.height / 2 - GameMenuJFrame.getSize().height / 2);
 
		//URL url1 = GameMenu.class.getResource("flaming.jpg");
		//Image b = getImage(url1);
		//(new Game()).getClass().getResource("/flaming.jpg").toString()
		BufferedImage image = ImageIO.read(GameMenu.class.getClass().getResource("/flaming.jpg"));
		ImagePanel panel = new ImagePanel(image);
		GameMenuJFrame.getContentPane().add(panel);
		// GameMenuJFrame.pack();

		JButton startBtn = new JButton("Игра с ботом");
		startBtn.setBackground(Color.BLACK);
		startBtn.setForeground(Color.WHITE);
		startBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		startBtn.setOpaque(true);
		startBtn.setLocation(45, 35);
		startBtn.setSize(180, 40);

		JButton withFriend = new JButton("Игра с другом");
		withFriend.setBackground(Color.BLACK);
		withFriend.setForeground(Color.WHITE);
		withFriend.setBorder(new RoundedBorder(20)); // 10 is the radius
		withFriend.setOpaque(true);
		withFriend.setLocation(45, 80);
		withFriend.setSize(180, 40);

		JButton networkBtn = new JButton("Сетевая игра");
		networkBtn.setBackground(Color.BLACK);
		networkBtn.setForeground(Color.WHITE);
		networkBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		networkBtn.setOpaque(true);
		networkBtn.setLocation(45, 125);
		networkBtn.setSize(180, 40);

		JButton replaysBtn = new JButton("Записи");
		replaysBtn.setBackground(Color.BLACK);
		replaysBtn.setForeground(Color.WHITE);
		replaysBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		replaysBtn.setOpaque(true);
		replaysBtn.setLocation(45, 170);
		replaysBtn.setSize(180, 40);
		
		JButton statBtn = new JButton("Статистика");
		statBtn.setBackground(Color.BLACK);
		statBtn.setForeground(Color.WHITE);
		statBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		statBtn.setOpaque(true);
		statBtn.setLocation(45, 215);
		statBtn.setSize(180, 40);

		JButton exitBtn = new JButton("Выход");
		exitBtn.setBackground(Color.BLACK);
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		exitBtn.setOpaque(true);
		exitBtn.setLocation(45, 260);
		exitBtn.setSize(180, 40);
		
		

		// StartGameFrame.setFocusable(true);

		panel.add(startBtn);
		panel.add(withFriend);
		panel.add(networkBtn);
		panel.add(replaysBtn);
		panel.add(statBtn);
		panel.add(exitBtn);
		GameMenuJFrame.setVisible(true);

		startBtn.addActionListener(new ActionListener() {
			private Thread recordingThread;

			public void actionPerformed(ActionEvent e) {
				mode = 1;
				getCurrentGame().paused = false;
				gameThread = new Thread(new GameMenu());
				gameThread.start();
				try {
					recordingThread = new Thread(new Recorder());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				recordingThread.start();

				GameMenuJFrame.setVisible(false);

			}
		});

		withFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 2;
				getCurrentGame().paused = false;
				gameThread = new Thread(new GameMenu());
				gameThread.start();
				
				try {
					recordingThread = new Thread(new Recorder());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				recordingThread.start();
				
				GameMenuJFrame.setVisible(false);
			}
		});

		replaysBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				point = GameMenuJFrame.getLocationOnScreen();
				ReplayMenu rl = new ReplayMenu();
				
			}
		});
		
		networkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 3;
				point = GameMenuJFrame.getLocationOnScreen();
				try {
					netMenu = new NetworkMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		statBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				point = GameMenuJFrame.getLocationOnScreen();
				try {
					StatisticsMenu rl = new StatisticsMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		// startBtn.addActionListener(new ActionListener()
		// { public void actionPerformed(ActionEvent e) { gm.mode = 1; } });

		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	// public static void stop() {
	// gameThread = null;
	// }

	@Override
	public void run() {

		if (mode == 0) {

		}
		if (mode == 1) {
			// System.out.println("35");
			GameMenu.stopped = false;
			currentGame = new GameVersusBot();
			currentGame.ball.initBall();
			currentGame.racquet.initRacquet();
			currentGame.ball.game = currentGame;
			currentGame.racquet.game = currentGame;
			currentGame.block.game = currentGame;

			(new Block(true)).placeBlocks();
			gameWrapper = new GameWrapper();
			try {
				gameWrapper.gameLoop();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Recorder.stopped = false;
			// try {
			// recordingThread = new Thread(new Recorder());
			// recordingThread.start();
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

		}
		if (mode == 2) {
			GameMenu.stopped = false;
			currentGame = new GameVersusFriend();
			currentGame.ball.initBall();
			currentGame.racquet.initRacquet();
			currentGame.ball.game = currentGame;
			currentGame.racquet.game = currentGame;
			currentGame.block.game = currentGame;

			(new Block(true)).placeBlocks();
			gameWrapper = new GameWrapper();
			try {
				gameWrapper.gameLoop();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Recorder.stopped = false;
		}

	}

	public static JFrame getGameMenuJFrame() {
		return GameMenuJFrame;
	}

	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void setCurrentGame(Game gm){
		currentGame = gm;
	}

}

class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	

}

class RoundedBorder implements Border {

	private int radius;

	RoundedBorder(int radius) {
		this.radius = radius;
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
}
