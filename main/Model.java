package breakout.main;

import java.util.ArrayList;
import breakout.items.*;
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
	}
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	public void setBricks(String brickData) {
		
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
	
	public void update() {
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
		score+=175;
	}
	
	public double getWidth(){
		return this.worldWidth;
	}
	public double getHeight(){
		return this.worldHeight;
	}
}
