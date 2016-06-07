package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import Game.Block;
import Game.GameVersusFriend;
import Game.GameViaNetwork;
import Game.GameWrapper;
import Menu.GameMenu;
import Menu.NetworkMenu;
import Replayer.Recorder;

public class Server implements Runnable {
	private int port;
	private ServerSocket serverSocket;
	private ServerCommunicator communicator;
	
	Socket server;

	public Server() throws IOException {
		serverSocket = new ServerSocket(0);
		this.port = serverSocket.getLocalPort();
		serverSocket.setSoTimeout(120000);

	}

	public int getPort() {
		return port;
	}

	@Override
	public void run() {

		while (true) {
			try {
				System.out.println("Waiting for client on port " + this.port + "...");
				server = serverSocket.accept();
				// Code here executes after establishing connection
				communicator = new ServerCommunicator(server);
				
				Thread td = new Thread(communicator);
				td.start();
				initNetworkGame();
				
				// System.out.println("Just connected to " +
				// server.getRemoteSocketAddress());
				// SYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYNC
				Thread.sleep(10);
				server.close();
				System.out.println("Socket closed");
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void initNetworkGame() {
		GameMenu.stopped = false;
		GameMenu.currentGame = new GameViaNetwork();
		GameMenu.currentGame.ball.initBall();
		GameMenu.currentGame.racquet.initRacquet();
		
		GameMenu.getCurrentGame().racquet.sc = communicator;
		GameMenu.getCurrentGame().upperRacquet.sc = communicator;
		
		GameMenu.currentGame.ball.game = GameMenu.currentGame;
		GameMenu.currentGame.racquet.game = GameMenu.currentGame;
		GameMenu.currentGame.block.game = GameMenu.currentGame;
		GameMenu.netMenu.networkJFrame.setVisible(false);
		// NetworkMenu.networkJFrame.setVisible(false);

		(new Block(true)).placeBlocks();
		String initialString = new String("");
		for(int i = 0; i < 20; i++){
			initialString += GameMenu.getCurrentGame().list.get(i).getTopX();
			initialString += ':';
			initialString +=  GameMenu.getCurrentGame().list.get(i).getTopY();
			initialString += '/';
		}
		initialString += "\r\n";
		
		communicator.sendToClient(initialString);
		
		GameMenu.gameWrapper = new GameWrapper();
		try {
			GameMenu.gameWrapper.gameLoop();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Recorder.stopped = false;

	}
}