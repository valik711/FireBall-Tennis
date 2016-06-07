package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator implements Runnable{

	DataOutputStream serverOut;
	DataInputStream serverIn;
	Socket server;
	
	
	public ServerCommunicator(Socket server) {
		this.server = server;
	}


	@Override
	public void run() {
		System.out.println("debug info");
		try {
			serverIn = new DataInputStream(server.getInputStream());
			serverOut = new DataOutputStream(server.getOutputStream());
			//sendToClient(str);
			//while(true){}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToClient(String str) {
		try {
			serverOut.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getFromClient(){
		try {
			return serverIn.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "err";
	
	}
	
	public void sendIntToClient(int i) {
		try {
			serverOut.writeInt(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getIntFromClient() {
		try {
			Thread.sleep(1);
			return serverIn.readInt();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		return 0;

	}
	
	public int clearStream(){
		;
		
		return 0;		
		
	}

}
