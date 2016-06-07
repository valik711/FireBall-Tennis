package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import Menu.GameMenu;

/**Blocks drawed at center of screen*/
public class Block {
	private int Y;
	private int X;
	private int WIDTH = 30;
	private int HEIGHT = 30;
	
	int xa = 0;
	boolean crashable;
	private boolean crashed;
	//Graphics2D g;
	public static Game game;

	public Block(boolean crashable) {
		game = GameMenu.getCurrentGame();
		this.crashable = crashable;
		
		//if(upper) this.Y = 20;
		//g.setColor(Color.BLUE);
	}
	
	public void placeBlocks(){
		
	
		Random rand = new Random(); 
		
		for(int j = 0; j<game.list.size(); j++){
			int segX = rand.nextInt(10)+1;
			int segY = rand.nextInt(10)+1;
			
			for(int i = 0; i<game.list.size(); i++)
				if(game.list.get(i).X == 32*(segX)-12 && game.list.get(i).Y == 32*(segY)+ 130){
					segX = rand.nextInt(10)+1;
					segY = rand.nextInt(10)+1;
					i = 0;
				}
			game.list.get(j).X = 32*(segX) - 12;
			game.list.get(j).Y = 32*(segY) + 130;
			game.list.get(j).setCrashed(false);
		}
		
	}

	public void paint(Graphics2D g) {
		g.fillRoundRect(X, Y, WIDTH, HEIGHT, 0, 0);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}
	
	public void hitAtLeftSide(){
		
	}
	
	public void move(){
//		if(Math.sqrt((X-game.ball.x +game.ball.getDiameter()/2)*
//				(X-game.ball.x +game.ball.getDiameter()/2) +
//				(Y-game.ball.y +game.ball.getDiameter()/2)*
//				(Y-game.ball.y +game.ball.getDiameter()/2)) <= game.ball.getDiameter()/2){
//			game.ball.xa *= -1;
//			game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			return;
//		}
//		if(Math.sqrt((X+WIDTH-game.ball.x +game.ball.getDiameter()/2)*
//				(X+WIDTH-game.ball.x +game.ball.getDiameter()/2) +
//				(Y-game.ball.y +game.ball.getDiameter()/2)*
//				(Y-game.ball.y +game.ball.getDiameter()/2)) <= game.ball.getDiameter()/2){
//			game.ball.xa *= -1;
//			game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			return;
//		}
//		if(Math.sqrt((X+WIDTH-game.ball.x +game.ball.getDiameter()/2)*
//				(X+WIDTH-game.ball.x +game.ball.getDiameter()/2) +
//				(Y+HEIGHT-game.ball.y +game.ball.getDiameter()/2)*
//				(Y+HEIGHT-game.ball.y +game.ball.getDiameter()/2)) <= game.ball.getDiameter()/2){
//			game.ball.xa *= -1;
//			game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			return;
//		}
//		
//		if(Math.sqrt((X-game.ball.x +game.ball.getDiameter()/2)*
//				(X-game.ball.x +game.ball.getDiameter()/2) +
//				(Y+HEIGHT-game.ball.y +game.ball.getDiameter()/2)*
//				(Y+HEIGHT-game.ball.y +game.ball.getDiameter()/2)) <= game.ball.getDiameter()/2){
//			game.ball.xa *= -1;
//			game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			return;
//		}
//		if(game.ball.y +game.ball.getDiameter()/2 >= this.Y && game.ball.y +game.ball.getDiameter()/2 <= this.Y+HEIGHT && game.ball.x + game.ball.getDiameter() == this.X && game.ball.xa >= 0)
//			{
//			game.ball.xa *= -1;
//			if(this.crashable) crashed = true;}
//		if(game.ball.y +game.ball.getDiameter()/2 >= this.Y && game.ball.y +game.ball.getDiameter()/2 < this.Y+HEIGHT && game.ball.x == this.X+WIDTH && game.ball.xa <= 0)
//			{
//			game.ball.xa *= -1;
//			if(this.crashable) crashed = true;
//			}
//		if(game.ball.x +game.ball.getDiameter()/2 >= this.X && game.ball.x +game.ball.getDiameter()/2 < this.X+WIDTH && game.ball.y + game.ball.getDiameter() == this.Y && game.ball.ya >= 0)
//			{game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			}
//		if(game.ball.x +game.ball.getDiameter()/2 >= this.X && game.ball.x +game.ball.getDiameter()/2 < this.X+WIDTH  && game.ball.y == this.Y+HEIGHT && game.ball.ya <= 0)
//			{game.ball.ya *= -1;
//			if(this.crashable) crashed = true;
//			}
		
		//if(game.ball.x )
		
		
			if(this.getBounds().intersects(game.ball.getBounds()) && game.ball.xa > 0 && game.ball.getX() + game.ball.getDiameter() >= this.X-5 && game.ball.getX() + game.ball.getDiameter() <= this.X+5){
				game.ball.xa = -1;
				this.setCrashed(true);
			}
			if(this.getBounds().intersects(game.ball.getBounds()) && game.ball.xa < 0 && game.ball.getX() >= this.X+WIDTH-5 && game.ball.getX() <= this.X+WIDTH+5){
				game.ball.xa = 1;
				this.setCrashed(true);
			}
			if(this.getBounds().intersects(game.ball.getBounds()) && game.ball.ya > 0 && game.ball.getY() + game.ball.getDiameter() >= this.Y-5 && game.ball.getY() + game.ball.getDiameter() <= this.Y+5){
				game.ball.ya = -1;
				this.setCrashed(true);
			}
			if(this.getBounds().intersects(game.ball.getBounds()) && game.ball.ya < 0 && game.ball.getY() >= this.Y+HEIGHT-5 && game.ball.getY() <= this.Y+HEIGHT+5){
				game.ball.ya = 1;
				this.setCrashed(true);
			}
		
		
		if(this.isCrashed()) this.X = 1000;
	};

	public void keyPressed(KeyEvent e) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle(X, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y;
	}
	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getTopX(){
		return X;
	}

	public boolean isCrashed() {
		return crashed;
	}

	public void setCrashed(boolean crashed) {
		this.crashed = crashed;
	}
}