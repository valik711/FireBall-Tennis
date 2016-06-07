package Menu;

import java.awt.Color;
import ScalaComputations.BestSort;
import ScalaComputations.JavaSort;

import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Replayer.Replayer;

public class ReplayMenu {
	public static JFrame replaysJFrame;
	public ArrayList<JButton> replayBtns;
	private Replayer repl;
	ArrayList<String> a;
	ArrayList<JButton> btns;
	static boolean pseudo = false;

	/** Results of played games */
	public ReplayMenu() {

		GameMenu.GameMenuJFrame.setVisible(false);
		replaysJFrame = new JFrame("Записи игры");
		replaysJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		replaysJFrame.setResizable(false);
		replaysJFrame.setLayout(null);
		replaysJFrame.setSize(640, 360);
		replaysJFrame.getContentPane().setBackground(Color.BLACK);

		JButton bestSortBtn = new JButton("<html>С<br>о<br>р<br>т</html>");

		bestSortBtn.setLocation(8, 8);
		bestSortBtn.setSize(50, 160);
		bestSortBtn.setBackground(Color.BLACK);
		bestSortBtn.setForeground(Color.WHITE);
		bestSortBtn.setBorder(new RoundedBorder(20));
		bestSortBtn.setOpaque(true);
		bestSortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				a = BestSort.sort();
				JavaSort js = new JavaSort();

				for (int i = 0; i < btns.size(); i++) {
					btns.get(i).setVisible(false);
				}

				for (int i = 0, j = 0, k = 0; k < a.size(); k++, i++) {
					JButton repBtn = new JButton(a.get(k));
					if (20 + 200 * i > 600) {
						i = 0;
						j++;
					}
					repBtn.setLocation(65 + 190 * i, 8 + j * 55);
					repBtn.setSize(185, 50);
					repBtn.setBackground(Color.BLACK);
					repBtn.setForeground(Color.WHITE);
					repBtn.setBorder(new RoundedBorder(20));
					repBtn.setOpaque(true);
					repBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								replaysJFrame.setVisible(false);
								String command = ((JButton) e.getSource()).getText();
								repl = new Replayer(command);
						}
					});

					replaysJFrame.add(repBtn);
					replayBtns.add(repBtn);
				}
			}
		});
		
		JButton psevdoBtn = new JButton("<html>П<br>с<br>е<br>в<br>д<br>о<br>к<br>о<br>д</html>");

		psevdoBtn.setLocation(8, 173);
		psevdoBtn.setSize(50, 160);
		psevdoBtn.setBackground(Color.BLACK);
		psevdoBtn.setForeground(Color.WHITE);
		psevdoBtn.setBorder(new RoundedBorder(20));
		psevdoBtn.setOpaque(true);
		psevdoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replaysJFrame.setVisible(false);
				//point = GameMenuJFrame.getLocationOnScreen();
				NotationMenu n = new NotationMenu();
				//repl = new Replayer(command);
			}
		});
		
		replaysJFrame.add(bestSortBtn);
		replaysJFrame.add(psevdoBtn);
		replaysJFrame.setLocation(GameMenu.point);
		replayBtns = new ArrayList<JButton>();
		final File folder = new File("saves/");

		int i = 0;
		int j = 0;

		a = new ArrayList<String>();
		btns = new ArrayList<JButton>();

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().startsWith("."))
				continue;
			JButton repBtn = new JButton(fileEntry.getName());
			btns.add(repBtn);
			a.add(fileEntry.getName());
			if (20 + 200 * i > 600) {
				i = 0;
				j++;
			}
			repBtn.setLocation(65 + 190 * i, 8 + j * 55);
			repBtn.setSize(185, 50);
			repBtn.setBackground(Color.BLACK);
			repBtn.setForeground(Color.WHITE);
			repBtn.setBorder(new RoundedBorder(20));
			repBtn.setOpaque(true);
			repBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					replaysJFrame.setVisible(false);
					String command = ((JButton) e.getSource()).getText();
					repl = new Replayer(command);
					// repl.replayFrame.setVisible(true);
					// repl.startReplay();
				}
			});

			// replayBtns.add(repBtn);
			replaysJFrame.add(repBtn);

			i++;
		}

		replaysJFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				GameMenu.point = replaysJFrame.getLocationOnScreen();
				replaysJFrame.setVisible(false);
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
		replaysJFrame.setVisible(true);
	}

}
