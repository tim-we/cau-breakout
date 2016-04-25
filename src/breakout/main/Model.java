package breakout.main;

import java.util.ArrayList;

import breakout.items.*;
import breakout.physics.Vector2D;
import java.util.Observable;
import breakout.animations.Animation;

public class Model extends Observable {
	
	/*
	 * All Components that define our actual game-state 
	 */
	private ArrayList<Ball> balls;
	
	private ArrayList<Brick> bricks;
	
	private ArrayList<Animation> animations;
	
	private Paddle paddle;
	
	private BarOfDeath bottomBar;
	
	private int score;
	
	private double worldWidth;
	private double worldHeight;
	
	private boolean hasEnded;
	
	/**
	 * Constructor: Creates a new Model with given Width and Height
	 * @param w - the width of the Model
	 * @param h - the height of the Model
	 */
	public Model(double w, double h) {
		worldWidth = w;
		worldHeight = h;
		
		bottomBar = new BarOfDeath((Config.WINDOW_ROWS-0.2) * Config.WINDOW_HEIGHT, worldWidth, 0.2 * Config.WINDOW_HEIGHT);
		
		reset();
		resetScore();
	}
	
	/**
	 * Resets the Model
	 */
	public void reset() {
		this.balls = new ArrayList<Ball>();
		this.bricks = new ArrayList<Brick>();
		this.animations = new ArrayList<Animation>();
		
		paddle = new Paddle(worldWidth);
		
		hasEnded = false;
		
		notifyViews();
	}
	
	/**
	 * Resets the score
	 */
	public void resetScore() {
		score = 0;
	};
	
	/* Getter for the Instance Variables */
	public ArrayList<Ball> getBalls() {	return balls; }
	
	public ArrayList<Brick> getBricks() { return bricks; }
	
	public Paddle getPaddle() {	return paddle; }
	
	public void clearBricks() {	bricks = new ArrayList<Brick>(); }
	
	public void clearBalls() { balls = new ArrayList<Ball>(); }
	
	public ArrayList<Animation> getAnimations() { return animations; }
	
	public BarOfDeath getBarOfDeath() {	return bottomBar; }
	
	public int getScore() { return this.score; }
	
	public double getWidth() { return this.worldWidth; }
	
	public double getHeight() {	return this.worldHeight; }
	
	public boolean hasEnded() { return hasEnded; } 
	
	/* adder for the Components */
	public void addBrick(Brick brick) {	if(brick != null) { bricks.add(brick); } }
	
	public void addBall(Ball ball) { if(ball != null) { balls.add(ball); } }
	
	//public void addView(View view) { this.views.add(view); }
	
	public void addAnimation(Animation anim) {	animations.add(anim); }
	
	public void addPoints(int points) {	
		score += points;
		
		if(points > 0) { System.out.println("added "+points+" points!"); }
	}
	
	public void levelCleared() {
		hasEnded = true;
	}
	
	/**
	 * Spawns a Ball at given Position and Velocity
 	 * @param pos - the Position where the Ball should spawn
	 * @param vel - the Velocity of the Ball at spawn
	 */
	public void spawnBall(Vector2D pos, Vector2D vel) {
		addAnimation(new breakout.animations.SpawnBall(pos, vel, this));
	}
	
	/**
	 * Updates the Model
	 */
	public void update() {		
		//animations:
		for(int i=0; i<animations.size(); i++) {
			Animation anim = animations.get(i);
			
			if(anim != null && anim.hasFinished()) {
				animations.remove(anim);
				i--;
			} else {
				anim.nextFrame();
			}
		}
		for (int i=0;i<balls.size();i++){
			Ball ball = balls.get(i);
			/* If there are more than one Ball, the balls get removed 
			 * when hitting the BarOFDeath instead of ending the game */
			if(ball != null && ball.isDead()){
				balls.remove(ball);
				i--;
			}
		}
		
		//update views
		notifyViews();
	}
	
	private void notifyViews() {
		setChanged();
		notifyObservers();
	}
	
}
