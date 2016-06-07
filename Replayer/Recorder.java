package Replayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import Game.GameVersusBot;
import Game.GameVersusFriend;
import Menu.GameMenu;

public class Recorder implements Runnable {
	
	boolean[] crashes = new boolean[20];

	public static boolean stopped = false;
	private String filename;

	public Recorder() throws IOException {
		
		for(int i = 0; i < 20; i++){
			crashes[i] = false;
		}
		try {
			Thread.sleep(10); // synchronization
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filename = "";
		if (GameMenu.getCurrentGame().getClass() == GameVersusFriend.class)
			filename += "f";
		if (GameMenu.getCurrentGame().getClass() == GameVersusBot.class)
			filename += "b";
		Calendar cal = Calendar.getInstance();
		filename += cal.get(Calendar.DAY_OF_MONTH);
		filename += "_";
		filename += cal.get(Calendar.MONTH);
		filename += "_";
		filename += (cal.get(Calendar.YEAR) - 2000);
		filename += "-";
		filename += cal.get(Calendar.HOUR);
		filename += "_";
		filename += cal.get(Calendar.MINUTE);
		filename += "_";
		filename += cal.get(Calendar.SECOND);

		File f = new File("saves/" + filename);
		f.createNewFile();
		try (FileWriter writer = new FileWriter("saves/" + filename, true)) {
			String initialString = new String("");
			for(int i = 0; i < 20; i++){
				initialString += GameMenu.getCurrentGame().list.get(i).getTopX();
				initialString += ':';
				initialString +=  GameMenu.getCurrentGame().list.get(i).getTopY();
				initialString += '/';
			}
			initialString += "\r\n";
			writer.write(initialString);
		}

	}

	@Override
	public void run() {
		while (!stopped) {
			
			try (FileWriter writer = new FileWriter("saves/" + filename, true)) {
				String frameString = new String("");
				frameString += GameMenu.getCurrentGame().ball.getX();
				frameString += '/';
				frameString += GameMenu.getCurrentGame().ball.getY();
				frameString += '/';
				
				frameString += GameMenu.getCurrentGame().upperRacquet.getX();
				frameString += '/';
				frameString += GameMenu.getCurrentGame().racquet.getX();
				frameString += '/';
				
				for(int i = 0; i < 20; i++){
					if(crashes[i] != GameMenu.getCurrentGame().list.get(i).isCrashed()) frameString += (i + "/");
				}
				writer.write(frameString);
				writer.write("\r\n");
				
				for(int i = 0; i < 20; i++){
					crashes[i] = GameMenu.getCurrentGame().list.get(i).isCrashed();
				}

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
