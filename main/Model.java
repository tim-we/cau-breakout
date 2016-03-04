package breakout.main;

import java.util.ArrayList;

//import breakout.levels.*;
import breakout.assets.BreakoutConstants;
import breakout.items.*;
import breakout.physics.Vector2D;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<View> views;
	
	private ArrayList<Ball> balls;
	
	private ArrayList<Brick> bricks;
	
	private Paddle paddle;
	
	//Level level;
	
	private int score = 0;
	
	private double worldWidth;
	private double worldHeight;
	
	public Model(double w, double h) {
		this.views = new ArrayList<View>();
		this.balls = new ArrayList<Ball>();
		this.bricks = new ArrayList<Brick>();
		//this.level = new Level(w);
		worldWidth = w;
		worldHeight = h;
		
		paddle = new Paddle();
		
		balls.add( new Ball(new Vector2D(worldWidth/2, worldHeight/2), new Vector2D(42, 200)) );
	}
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	public void addBrick(Brick brick) {
		if(brick != null) { bricks.add(brick); }
	}
	
	public void clearBricks() {
		bricks.clear();
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
	
	public void addPoints(int points){
		score += points;
	}
	
	public double getWidth(){
		return this.worldWidth;
	}
	public double getHeight(){
		return this.worldHeight;
	}
}
