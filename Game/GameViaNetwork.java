package Game;

import javax.swing.JOptionPane;

import Menu.GameMenu;
import Replayer.Recorder;

public class GameViaNetwork extends Game {
	public GameViaNetwork() {
		// System.out.println("dcsdc");
	}

	@SuppressWarnings("deprecation")
	public void gameOver(boolean lowerWins) throws Throwable {
		Recorder.stopped = true;
		GameMenu.stopped = true;
		Object[] options = { "В меню" };
		int chose;
		if (lowerWins)
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 2", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		else {
			chose = JOptionPane.showOptionDialog(this, "Игра окончена. Победил игрок 1", "Игра окончена",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		}

		if (chose == 0) {
			
				GameMenu.getGameMenuJFrame().setVisible(false);
				GameMenu.netMenu.networkJFrame.setVisible(true);
				GameWrapper.getGameFrame().setVisible(false);


		}
	}
}
