package Game;

import java.util.Random;

import Menu.GameMenu;

public class GameVersusBot extends Game {
	public GameVersusBot() {

	}

	public void move() throws Throwable {
		GameMenu.serverMode = 999;
		Random rn = new Random();
		//GameMenu.getCurrentGame().upperRacquet.xa = GameMenu.getCurrentGame().ball.xa;
		if(rn.nextInt(100) < 50) GameMenu.getCurrentGame().upperRacquet.xa = 1 + GameMenu.getCurrentGame().ball.xa;
		if(rn.nextInt(100) > 50) GameMenu.getCurrentGame().upperRacquet.xa = -1 + GameMenu.getCurrentGame().ball.xa;
		//if(GameMenu.getCurrentGame().upperRacquet.xa == GameMenu.getCurrentGame().ball.xa && (180 - GameMenu.getCurrentGame().ball.x) < (130 - GameMenu.getCurrentGame().upperRacquet.x)) GameMenu.getCurrentGame().upperRacquet.xa = 0;
		ball.move();
		racquet.move();
		upperRacquet.move();
		// list.get(1).move();
		for (int i = 0; i < 20; i++)
			list.get(i).move();
	}
}
