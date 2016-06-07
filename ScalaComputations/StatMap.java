package ScalaComputations;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Menu.GameMenu;
import scala.Tuple2;
import scala.collection.mutable.ArrayBuffer;

class MyCanvas extends JComponent {

  public void paint(Graphics g) {
	  
	ArrayBuffer<Tuple2<Object, Object>> a = ScalaComputations.Statistics.graphStat();
	for (int i = 0; i < a.size()/50; i++){
		
		g.drawRect(a.head()._1$mcI$sp(), a.head()._2$mcI$sp(), 1, 1);
		a.trimStart(50);
	}
    g.drawRect (130, 20, 100, 15);  
    g.drawRect (130, 530, 100, 15);
  }
}

public class StatMap {
  public static void drawRect() {
    JFrame window = new JFrame();
    window.setBounds(0, 0, 350, 590);
    window.getContentPane().add(new MyCanvas());
    window.addWindowListener(new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			GameMenu.point = window.getLocationOnScreen();
			window.setVisible(false);

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
    window.setVisible(true);
  }
}