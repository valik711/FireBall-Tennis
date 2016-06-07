package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Menu.GameMenu;

public class Ball {
	private static final int DIAMETER = 16;
	int x = 400;
	int y = 300;
	int xa = 1;
	int ya = 1;
	public static Game game;

	public Ball() {
		
		resetCoords();
	}
	
	int getDiameter(){
		return DIAMETER;
	}

	public void initBall(){
		game = GameMenu.getCurrentGame();
	}
	
	public void move() throws Throwable {
		if (getX() + xa < 0)
			xa = 1;
		if (getX() + xa > game.getWidth() - DIAMETER)
			xa = -1;
		if (getY() + ya < 0)
			ya = 1;
		if (getY() + ya > game.getHeight() - DIAMETER)
			game.gameOver(true);
		if (getY() + ya == 0)
			game.gameOver(false);
		if (collisionLower()){
			ya = -1;
			setY(game.racquet.getTopY() - DIAMETER);
			//game.racquet.reduce();
		}
		if (collisionUpper()){
			ya = 1;
			setY(game.upperRacquet.getTopY() + 15);

		}
		
		
		setX(getX() + xa);
		setY(getY() + ya);
	}

	private boolean collisionLower() {
		
		return game.racquet.getBounds().intersects(getBounds());
		
	}
	
	private boolean collisionUpper() {
		//if(y < 25 || y > 45) return false;
		return game.upperRacquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(getX(), getY(), DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), DIAMETER, DIAMETER);
	}
	
	public void resetCoords(){
		this.setX(100);
		this.setY(50);
		this.xa = 1;
		this.ya = 1;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}