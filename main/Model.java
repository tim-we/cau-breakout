package breakout.main;

import java.util.ArrayList;

import breakout.assets.BreakoutConstants;
import breakout.items.*;
import breakout.physics.Vector2D;
import java.util.Observable;
import breakout.animations.Animation;

public class Model extends Observable {
	
	private ArrayList<Ball> balls;
	
	private ArrayList<Brick> bricks;
	
	private ArrayList<Animation> animations;
	
	private Paddle paddle;
	
	private BarOfDeath bottomBar;
	
	private int score;
	
	private double worldWidth;
	private double worldHeight;
	
	//constructor
	public Model(double w, double h) {
		worldWidth = w;
		worldHeight = h;
		
		bottomBar = new BarOfDeath((BreakoutConstants.WINDOW_ROWS-0.2) * BreakoutConstants.WINDOW_HEIGHT, worldWidth, 0.2 * BreakoutConstants.WINDOW_HEIGHT);
		
		reset();
	}
	
	public void reset() {
		this.balls = new ArrayList<Ball>();
		this.bricks = new ArrayList<Brick>();
		this.animations = new ArrayList<Animation>();
		
		score = 0;
		
		paddle = new Paddle(worldWidth);
		
		notifyViews();
	}
	
	//getter
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
	
	//"adder"
	public void addBrick(Brick brick) {	if(brick != null) { bricks.add(brick); } }
	
	public void addBall(Ball ball) { if(ball != null) { balls.add(ball); } }
	
	//public void addView(View view) { this.views.add(view); }
	
	public void addAnimation(Animation anim) {	animations.add(anim); }
	
	public void addPoints(int points) {	score += points; }
	
	public void spawnBall(Vector2D pos, Vector2D vel) {
		addAnimation(new breakout.animations.SpawnBall(pos, vel, this));
	}
	
	//other
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
		
		//update views
		notifyViews();
	}
	
	private void notifyViews() {
		setChanged();
		notifyObservers();
	}
	
}
