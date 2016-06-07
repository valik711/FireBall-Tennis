package Menu;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Replayer.Replayer;

public class NotationMenu {
	public static JFrame notationJFrame;
	public ArrayList<JButton> replayBtns;
	private Pseudo ps;
	ArrayList<String> a;
	ArrayList<JButton> btns;
	static boolean pseudo = false;

	/** Results of played games */
	public NotationMenu() {

		GameMenu.GameMenuJFrame.setVisible(false);
		notationJFrame = new JFrame("Псевдокод нотаций");
		notationJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		notationJFrame.setResizable(false);
		notationJFrame.setLayout(null);
		notationJFrame.setSize(640, 360);
		notationJFrame.getContentPane().setBackground(Color.BLACK);


		notationJFrame.setLocation(GameMenu.point);
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
			repBtn.setLocation(20 + 200 * i, 8 + j * 55);
			repBtn.setSize(195, 50);
			repBtn.setBackground(Color.BLACK);
			repBtn.setForeground(Color.WHITE);
			repBtn.setBorder(new RoundedBorder(20));
			repBtn.setOpaque(true);
			repBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					notationJFrame.setVisible(false);
					String command = ((JButton) e.getSource()).getText();
					ps = new Pseudo(command);
				}
			});
			notationJFrame.add(repBtn);
			i++;
		}

		notationJFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				GameMenu.point = notationJFrame.getLocationOnScreen();
				notationJFrame.setVisible(false);
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
		notationJFrame.setVisible(true);
	}

}
