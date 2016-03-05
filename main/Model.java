package breakout.main;

import java.util.ArrayList;

//import breakout.levels.*;
import breakout.assets.BreakoutConstants;
import breakout.items.*;
import breakout.physics.Vector2D;
import java.util.Observable;
import breakout.animations.Animation;

public class Model extends Observable {
	
	private ArrayList<View> views;
	
	private ArrayList<Ball> balls;
	
	private ArrayList<Brick> bricks;
	
	private ArrayList<Animation> animations;
	
	private Paddle paddle;
	
	private BarOfDeath bottomBar;
	
	private int score = 0;
	
	private double worldWidth;
	private double worldHeight;
	
	public Model(double w, double h) {
		this.views = new ArrayList<View>();
		this.balls = new ArrayList<Ball>();
		this.bricks = new ArrayList<Brick>();
		this.animations = new ArrayList<Animation>();

		worldWidth = w;
		worldHeight = h;
		
		paddle = new Paddle();
		
		bottomBar = new BarOfDeath((BreakoutConstants.WINDOW_ROWS-0.2) * BreakoutConstants.WINDOW_HEIGHT, worldWidth, 0.2 * BreakoutConstants.WINDOW_HEIGHT);
		
		balls.add( new Ball(new Vector2D(worldWidth/2, worldHeight/2), new Vector2D(3 * BreakoutConstants.WINDOW_HEIGHT, 7 * BreakoutConstants.WINDOW_HEIGHT)) );
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
	
	public BarOfDeath getBarOfDeath() {
		return bottomBar;
	}
	
	public ArrayList<Animation> getAnimations() {
		return animations;
	}
	
	public void addAnimation(Animation anim) {
		animations.add(anim);
	}
	
	public void update() {		
		//garbage-collect animations:
		for(Animation anim : animations) {
			if(anim.hasFinished()) {
				animations.remove(anim);
			}
		}
		
		//update views
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
