package breakout.items;

import breakout.main.Config;
import breakout.physics.*;

public class Paddle extends PhysicsObject{

	private double paddleWidth = Config.normalPaddle;
	
	private double availableWidth = 0;
	
	private double speed = 0;
	
	private boolean reverse = false;
	
	/**
	 * Creates a new paddle with given width
	 * @param width Width of the paddle
	 */
	public Paddle(double width) {
		/* Sets the Position of the Paddle on the middle of the bottom row */
		this.Position = new Vector2D(
			((Config.WINDOW_COLUMNS/2d)-2) * Config.WINDOW_WIDTH,
			(Config.WINDOW_ROWS-1) * Config.WINDOW_HEIGHT
		);
		/* sets up the BoundingBox for the paddle */
		this.setBBox(paddleWidth, Config.WINDOW_HEIGHT);
		
		availableWidth = width;
	}
	
	public boolean isReversed() {
		return this.reverse;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * changes the value of the reverse-constant
	 */
	public void toggleReverse(){
		this.reverse = !reverse;
	}
	
	/**
	 * Changes the width of the paddle
	 * @param size The value that should be added to the actual width
	 */
	public void changePaddleWidth(double size){
		this.paddleWidth += size;
		this.setBBox(paddleWidth, Config.WINDOW_HEIGHT);
	}
	
	/**
	 * Moves the paddle on the x-axis
	 * 	This code should probably be in the Controller
	 * 	but to us it made more sense to put it in here.
	 * @param d the distance the Paddle shall get moved
	 */
	public void move(double d){
		d = reverse ? (-d) : d;

		setPosition (getPosition().getX() + d);
		
		if(Math.abs(d) > Math.abs(speed)) { speed = d; }
		else { speed = 0.6 * speed + 0.4*d;	}
	}
	
		public void setPosition (double x){
		
		x = Math.max(0, Math.min(x, availableWidth-paddleWidth));
		
		Position.setX(x);
	}

	public double getWidth() {
	  return this.paddleWidth;
	}
}
