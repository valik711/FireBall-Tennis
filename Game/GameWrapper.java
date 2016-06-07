package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Menu.GameMenu;

public class GameWrapper {

	static JFrame GameFrame;
	static Game game;

	public GameWrapper() {

		GameFrame = new JFrame("Теннис");
		GameFrame.setSize(360, 600);
		game = GameMenu.getCurrentGame();
		GameFrame.add(game);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		GameFrame.setLocation(dim.width / 2 - GameFrame.getSize().width / 2,
				dim.height / 2 - GameFrame.getSize().height / 2);
		GameFrame.setResizable(false);
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.setVisible(true);

	}

	public static JFrame getGameFrame() {
		return GameFrame;
	}

	public static void gameLoop() throws Throwable {
		long i = 0;
		while (!GameMenu.stopped) {
			GameMenu.getCurrentGame();
			if (!Game.paused) {
				game.move();
				game.repaint();
				if (i % 20 == 0) {
					
					if (game.racquet.sc != null) {
						//game.racquet.cc.clearStream();
						game.racquet.sc.clearStream();
						
						game.racquet.sc.sendIntToClient(-game.ball.x);
						game.racquet.sc.sendIntToClient(-game.ball.y);
						//Thread.sleep(0, 500);
						}
					if (game.racquet.cc != null) { 
						int x = game.racquet.cc.getIntFromServer();
						int y = game.racquet.cc.getIntFromServer();	
						if(x < 0) game.ball.x = -x;
						if(y < 0) game.ball.y = -y;
						
					}
					
				}

				if (game.racquet.cc != null) {
					game.racquet.cc.sendIntToServer(game.upperRacquet.x);
					Thread.sleep(0, 500);
				}

				if (game.racquet.sc != null) {
					game.upperRacquet.x = game.upperRacquet.sc.getIntFromClient();
				}

				if (game.racquet.sc != null){
					game.racquet.sc.sendIntToClient(game.racquet.x);
					Thread.sleep(0, 500);
				}
				if (game.racquet.cc != null) {
					game.racquet.x = game.racquet.cc.getIntFromServer();
				}

				i++;

				Thread.sleep(4);
				if (Thread.interrupted()) {
					break;
				}
			}

			// else break;
		}

	}

	public static void restartGame() {
		GameMenu.gameThread.stop();
		GameMenu.gameThread = new Thread(new GameMenu());
		GameMenu.gameThread.start();
	}

}
