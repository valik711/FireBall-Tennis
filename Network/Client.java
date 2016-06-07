package Network;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import Game.Block;
import Game.GameViaNetwork;
import Game.GameWrapper;
import Menu.GameMenu;
import Replayer.Recorder;

public class Client implements Runnable{

	private String serverIP;
	private int port;
	Socket client;
	DataOutputStream clientOut;
	OutputStream output;
	DataInputStream clientIn;
	InputStream input;
	private ClientCommunicator communicator;

	public Client(String ip, int port) throws IOException {
		this.serverIP = ip;
		this.port = port;
		System.out.println("Connecting to " + serverIP + " on port " + port);
		client = new Socket(serverIP, port);
		System.out.println("Just connected to " + client.getRemoteSocketAddress());
		
		GameMenu.netMenu.networkJFrame.setVisible(false);
		output = client.getOutputStream();
		clientOut = new DataOutputStream(output);
		clientOut.writeUTF("hi from client");
		input = client.getInputStream();
		clientIn = new DataInputStream(input);
		communicator = new ClientCommunicator(client);
		Thread td = new Thread(communicator);
		
		td.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(clientIn.readUTF());
		
		
	}

	void closeConnection() throws IOException {
		client.close();
	}

	private void initNetworkGame() {
		GameMenu.stopped = false;
		GameMenu.currentGame = new GameViaNetwork();
		GameMenu.currentGame.ball.initBall();
		GameMenu.currentGame.racquet.initRacquet();
		
		GameMenu.getCurrentGame().racquet.cc = communicator;
		GameMenu.getCurrentGame().upperRacquet.cc = communicator;
		
		GameMenu.currentGame.ball.game = GameMenu.currentGame;
		GameMenu.currentGame.racquet.game = GameMenu.currentGame;
		GameMenu.currentGame.block.game = GameMenu.currentGame;
		GameMenu.netMenu.networkJFrame.setVisible(false);
		String s = communicator.getFromServer();
		
		int l = 0;
		int r = 0;
		for (int i = 0; i < 20; i++) {
			do {

				r++;
			} while (s.charAt(r) != ':');
			int first = Integer.parseInt(s.substring(l, r));
			l = r + 1;
			do {

				r++;
			} while (s.charAt(r) != '/');
			int second = Integer.parseInt(s.substring(l, r));
			l = r + 1;
			GameMenu.getCurrentGame().list.get(i).setX(first);
			GameMenu.getCurrentGame().list.get(i).setY(second);
			
		}
		System.out.println(s);
		
		GameMenu.gameWrapper = new GameWrapper();
		try {
			GameMenu.gameWrapper.gameLoop();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Recorder.stopped = false;

	}

	void send(String s) throws IOException {
		clientOut.writeUTF(client.getLocalSocketAddress().toString());

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		initNetworkGame();
	}

}
