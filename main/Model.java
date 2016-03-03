package breakout.main;

import java.util.ArrayList;

import breakout.assets.BreakoutConstants;
import breakout.items.*;
import breakout.physics.Vector2D;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<View> views;
	
	private ArrayList<Brick> bricks;
	
	private ArrayList<Ball> balls;
	
	private Paddle paddle;
	
	private int score = 0;
	
	private double worldWidth;
	private double worldHeight;
	
	public Model(double w, double h) {
		this.views = new ArrayList<View>();
		this.bricks = new ArrayList<Brick>();
		this.balls = new ArrayList<Ball>();
		
		worldWidth = w;
		worldHeight = h;
		
		paddle = new Paddle();
		
		balls.add( new Ball(new Vector2D(worldWidth/2, worldHeight/2), new Vector2D(42, 200)) );
	}
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	public void setBricks(String brickData) {
		//remove all existing bricks
		bricks = new ArrayList<Brick>();
		
		System.out.println("loading level: " + brickData);
		
		int bricksPerRow = (int) Math.floor( (worldWidth - BreakoutConstants.BRICK_X_OFFSET) / (Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET) );
		
		int xk = 0;
		double xPos;
		double yPos = BreakoutConstants.BRICK_Y_OFFSET;
		
		for(int i=0; i<brickData.length(); i++) {
			byte brickType = -1;
			
			switch(brickData.charAt(i)) {
				case '0':
					brickType = 0; break;
				case '1':
					brickType = 1; break;
				case '2':
					brickType = 2; break;
				case '.': //forced line break
					xk=bricksPerRow-1;
				
			}
			
			xPos = BreakoutConstants.BRICK_X_OFFSET + xk * (Brick.brickWidth + BreakoutConstants.BRICK_X_OFFSET);
			
			if(brickType >= 0) { bricks.add(new Brick(xPos, yPos, (byte)brickType)); }
			xk++;
			
			if(xk==bricksPerRow) {
				xk = 0;
				yPos += Brick.brickHeight + BreakoutConstants.BRICK_Y_OFFSET;
			}		
		}
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public void updateViews() {
		notifyViews();
	}
	
	private void notifyViews() {
		for(View view : views) {
			view.update(this);
		}
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void addPoints(){
		score += 175;
	}
	
	public double getWidth(){
		return this.worldWidth;
	}
	public double getHeight(){
		return this.worldHeight;
	}
}
