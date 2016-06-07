package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Network.Client;
import Network.Server;

public class NetworkMenu implements Runnable {

	private boolean hostMode;

	public static JFrame networkJFrame;
	ImagePanel panel;
	JButton changeModeBtn;
	JButton connectBtn;

	JLabel currentMode;
	JLabel hostMessage;
	JTextField ip;
	JTextField port;
	
	public int serverMode = 999;
	Thread serverDaemon;
	
	private boolean serverCreated;

	public ArrayList<JButton> replayBtns;
	/** Network menu contains all buttons used for setting up a network game
	 * @throws IOException */
	public NetworkMenu() throws IOException {
		GameMenu.GameMenuJFrame.setVisible(false);
		networkJFrame = new JFrame("Сетевая игра");
		networkJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		networkJFrame.setResizable(false);
		networkJFrame.setLayout(null);
		networkJFrame.setSize(640, 360);
		networkJFrame.getContentPane().setBackground(Color.BLACK);

		buildInterface();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		networkJFrame.setLocation(GameMenu.point);

		BufferedImage image = ImageIO.read(GameMenu.class.getClass().getResource("/flaming.jpg"));
		panel = new ImagePanel(image);
		networkJFrame.getContentPane().add(panel);

		networkJFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {
				GameMenu.point = networkJFrame.getLocationOnScreen();
				networkJFrame.setVisible(false);
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
		networkJFrame.setVisible(true);
	}

	void buildInterface() {

		currentMode = new JLabel("Вы в режиме клиента");

		ip = new RoundJTextField(5);
		ip.setText(" Введите IP хоста");
		ip.setForeground(Color.WHITE);
		ip.setLocation(45, 180);
		ip.setSize(150, 30);
		ip.setBackground(Color.BLACK);
		ip.setVisible(!hostMode);
		networkJFrame.add(ip);

		port = new RoundJTextField(5);
		port.setText(" Введите порт");
		port.setForeground(Color.WHITE);
		port.setLocation(45, 220);
		port.setSize(150, 30);
		port.setBackground(Color.BLACK);
		port.setVisible(!hostMode);
		networkJFrame.add(port);

		connectBtn = new JButton("Подключиться");
		connectBtn.setSize(150, 30);
		connectBtn.setLocation(45, 260);
		connectBtn.setBackground(Color.BLACK);
		connectBtn.setForeground(Color.WHITE);
		connectBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		connectBtn.setOpaque(true);
		connectBtn.setVisible(!hostMode);
		connectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GameMenu.serverMode = 0;
					Client client = new Client(ip.getText(), Integer.parseInt(port.getText()));
					serverDaemon = new Thread(client);
					serverDaemon.start();
					//networkJFrame.addKeyListener(client);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		networkJFrame.add(connectBtn);

		hostMessage = new JLabel();
		hostMessage.setForeground(Color.WHITE);
		hostMessage.setLocation(45, 140);
		hostMessage.setSize(220, 100);
		hostMessage.setBackground(Color.BLACK);
		hostMessage.setVisible(false);
		networkJFrame.add(hostMessage);

		currentMode.setForeground(Color.WHITE);
		currentMode.setLocation(45, 20);
		currentMode.setSize(195, 50);
		currentMode.setBackground(Color.BLACK);

		changeModeBtn = new JButton("Сменить режим");
		changeModeBtn.setSize(150, 30);
		changeModeBtn.setLocation(45, 60);
		changeModeBtn.setBackground(Color.BLACK);
		changeModeBtn.setForeground(Color.WHITE);
		changeModeBtn.setBorder(new RoundedBorder(20)); // 10 is the radius
		changeModeBtn.setOpaque(true);
		changeModeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostMode = !hostMode;
				ip.setVisible(!hostMode);
				port.setVisible(!hostMode);
				connectBtn.setVisible(!hostMode);
				if (hostMode) {
					currentMode.setText("Вы в режиме хоста");
					try {
						GameMenu.serverMode = 1;
						Server ser = new Server();
						serverDaemon = new Thread(ser);
						serverDaemon.start();
						serverCreated = true;
						hostMessage.setText(
								"<html>Ожидание клиента <br>" + InetAddress.getLocalHost().getCanonicalHostName() + ":"
										+ ser.getPort() + "</html>");

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					{
					currentMode.setText("Вы в режиме клиента");
					if(serverCreated){
						serverDaemon.stop();
						serverCreated = false;
					}
					
					}
				hostMessage.setVisible(hostMode);
				

			}
		});

		networkJFrame.add(currentMode);
		networkJFrame.add(changeModeBtn);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

class RoundJTextField extends JTextField {
	private Shape shape;

	public RoundJTextField(int size) {
		super(size);
		setOpaque(false);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	}

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}
		return shape.contains(x, y);
	}
}
