package Menu;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Replayer.Replayer;

public class Pseudo {
	public static JFrame pseudoJFrame;
	public ArrayList<JButton> replayBtns;
	private JPanel pseudoPanel;
	private JTextArea display;
	private JScrollPane scroll;
	private Replayer repl;
	ArrayList<String> a;
	ArrayList<JButton> btns;

	/** Results of played games */
	public Pseudo(String s) {

		GameMenu.GameMenuJFrame.setVisible(false);
		pseudoJFrame = new JFrame("Псевдокод");
		pseudoJFrame.getContentPane().setBackground(Color.BLACK);
		
		
		JPanel middlePanel = new JPanel ();
		middlePanel.setBackground(Color.BLACK);
		pseudoJFrame.setForeground(Color.WHITE);
		EtchedBorder bord = new EtchedBorder();
		TitledBorder tBord = new TitledBorder("Псевдокод игры " + s);
		tBord.setTitleColor(Color.WHITE);
	    middlePanel.setBorder (tBord);

	    JTextArea display = new JTextArea (19, 50);
	    display.setText(ScalaComputations.Pseudo.generatePseudo(s));
	    display.setBackground(Color.BLACK);
	    display.setForeground(Color.WHITE);
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

	    //Add Textarea in to middle panel
	    middlePanel.add (scroll);

	    // My code
	   
	    pseudoJFrame.add ( middlePanel );
	    pseudoJFrame.pack ();
	    pseudoJFrame.setLocationRelativeTo ( null );
	    pseudoJFrame.setVisible ( true );
	    

		pseudoJFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				GameMenu.point = pseudoJFrame.getLocationOnScreen();
				pseudoJFrame.setVisible(false);
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
		pseudoJFrame.setVisible(true);
	}

}
