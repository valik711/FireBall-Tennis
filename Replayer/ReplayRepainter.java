package Replayer;

import Menu.GameMenu;

public class ReplayRepainter implements Runnable {

	ReplayGame game;

	public ReplayRepainter(ReplayGame g) {
		game = g;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (game.replayMove() == 1) {
					game.gameOver(true);
					break;
				}
				game.repaint();
				Thread.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
