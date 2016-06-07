package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientCommunicator implements Runnable {

	DataOutputStream clientOut;
	DataInputStream clientIn;
	Socket client;

	public ClientCommunicator(Socket clt) {
		this.client = clt;
	}

	@Override
	public void run() {
		System.out.println("debug info");
		try {
			clientIn = new DataInputStream(client.getInputStream());
			clientOut = new DataOutputStream(client.getOutputStream());
			// serverOut.writeUTF("privet");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendToServer(String str) {
		try {
			clientOut.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sendIntToServer(int i) {
		try {
			clientOut.writeInt(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getIntFromServer() {
		try {
			Thread.sleep(1);
			return clientIn.readInt();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		return 0;

	}
	
	public String getFromServer() {
		try {
			Thread.sleep(1);
			return clientIn.readUTF();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		return "err";

	}
	
	public int streamSize(){
		try {
			Thread.sleep(1);
			return clientIn.available();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
		
	}
	
 

}
