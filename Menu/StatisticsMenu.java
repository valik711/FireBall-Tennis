package Menu;

import java.awt.Color;

import ScalaComputations.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Replayer.Replayer;


public class StatisticsMenu {
	public static JFrame statisticsJFrame;
	public ArrayList<JButton> replayBtns;
	private Replayer repl;
	ArrayList<String> a;
	ArrayList<JButton> btns;
/** Results of played games
 * @throws IOException */
	public StatisticsMenu() throws IOException {

		GameMenu.GameMenuJFrame.setVisible(false);
		statisticsJFrame = new JFrame("Статистика игры");
		statisticsJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		statisticsJFrame.setResizable(false);
		statisticsJFrame.setLayout(null);
		statisticsJFrame.setSize(640, 360);
		statisticsJFrame.getContentPane().setBackground(Color.BLACK);

		BufferedImage image = ImageIO.read(GameMenu.class.getClass().getResource("/flaming.jpg"));
		ImagePanel panel = new ImagePanel(image);
		statisticsJFrame.getContentPane().add(panel);
		statisticsJFrame.setLocation(GameMenu.point);
		
		JLabel statLbl1 = new JLabel("Статистика игры");
		statLbl1.setForeground(Color.WHITE);
		statLbl1.setLocation(45, 60);
		statLbl1.setSize(200, 20);
		statLbl1.setBackground(Color.BLACK);
		statLbl1.setVisible(true);
		
		String stat2 = new String("Всего игр: ");
		stat2 += Statistics.numGames();
		JLabel statLbl2 = new JLabel(stat2);
		statLbl2.setForeground(Color.WHITE);
		statLbl2.setLocation(45, 150);
		statLbl2.setSize(200, 20);
		statLbl2.setBackground(Color.BLACK);
		statLbl2.setVisible(true);
		
		String stat3 = new String("Игр с ботом: ");
		stat3 += Statistics.numGamesWithBot();
		JLabel statLbl3 = new JLabel(stat3);
		statLbl3.setForeground(Color.WHITE);
		statLbl3.setLocation(45, 170);
		statLbl3.setSize(200, 20);
		statLbl3.setBackground(Color.BLACK);
		statLbl3.setVisible(true);
		
		String stat4 = new String("Самая долгая игра: ");
		stat4 += Statistics.longestGame();
		stat4 += " c";
		JLabel statLbl4 = new JLabel(stat4);
		statLbl4.setForeground(Color.WHITE);
		statLbl4.setLocation(45, 190);
		statLbl4.setSize(200, 20);
		statLbl4.setBackground(Color.BLACK);
		statLbl4.setVisible(true);
		
		String stat5 = new String("Средняя продолж.: ");
		stat5 += Statistics.averageGameDuration();
		stat5 += " c";
		JLabel statLbl5 = new JLabel(stat5);
		statLbl5.setForeground(Color.WHITE);
		statLbl5.setLocation(45, 210);
		statLbl5.setSize(200, 20);
		statLbl5.setBackground(Color.BLACK);
		statLbl5.setVisible(true);
		
		String stat6 = new String("Путь шара: ");
		stat6 += Statistics.totalDistanceTravelled();
		stat6 += " px";
		JLabel statLbl6 = new JLabel(stat6);
		statLbl6.setForeground(Color.WHITE);
		statLbl6.setLocation(45, 230);
		statLbl6.setSize(280, 20);
		statLbl6.setBackground(Color.BLACK);
		statLbl6.setVisible(true);
		
		String stat7 = new String("Разбито блоков: ");
		stat7 += Statistics.blocksDestroyed();
		JLabel statLbl7 = new JLabel(stat7);
		statLbl7.setForeground(Color.WHITE);
		statLbl7.setLocation(45, 250);
		statLbl7.setSize(200, 20);
		statLbl7.setBackground(Color.BLACK);
		statLbl7.setVisible(true);
		
		String stat8 = new String("Выигрыши игрока: ");
		stat8 += Statistics.playerWins();
		JLabel statLbl8 = new JLabel(stat8);
		statLbl8.setForeground(Color.WHITE);
		statLbl8.setLocation(45, 270);
		statLbl8.setSize(200, 20);
		statLbl8.setBackground(Color.BLACK);
		statLbl8.setVisible(true);
		
		
		panel.add(statLbl1);
		panel.add(statLbl2);
		panel.add(statLbl3);
		panel.add(statLbl4);
		panel.add(statLbl5);
		panel.add(statLbl6);
		panel.add(statLbl7);
		panel.add(statLbl8);
		
		
		(new StatMap()).drawRect();;
		

		statisticsJFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				GameMenu.point = statisticsJFrame.getLocationOnScreen();
				statisticsJFrame.setVisible(false);
				GameMenu.GameMenuJFrame.setLocation(GameMenu.point);
				GameMenu.GameMenuJFrame.setVisible(true);

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
		});
		statisticsJFrame.setVisible(true);
	}

}
