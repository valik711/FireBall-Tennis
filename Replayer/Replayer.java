package Replayer;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Game.Game;
import Game.GameWrapper;
import Menu.GameMenu;

public class Replayer{
	public static JFrame replayFrame;
	private ReplayGame game;
	ReplayRepainter repainter;
	Thread repaintThread;
	
	public ReplayGame getGame() {
		return game;
	}

	public Replayer(String filepath){
		replayFrame = new JFrame("Теннис");
		replayFrame.setSize(360, 600);
		try {
			game = new ReplayGame(filepath);
			Thread.sleep(10);
			repainter = new ReplayRepainter(game);
			repaintThread = new Thread(repainter);
			repaintThread.start();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		replayFrame.add(game);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		replayFrame.setLocation(dim.width/2-replayFrame.getSize().width/2, dim.height/2-replayFrame.getSize().height/2);
		replayFrame.setResizable(false);
		replayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		replayFrame.setVisible(true);
		//startReplay();
		System.out.println("new replayer object created for file " + filepath);
	}
	
	
	
}
