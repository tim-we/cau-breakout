package breakout.items;

import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;

public class Paddle extends PhysicsObject{

	private double paddleWidth = BreakoutConstants.normalPaddle;
	
	private double availableWidth = 0;
	
	private Color color = new Color (229,55,203);
	
	private double speed = 0;
	
	private boolean reverse = false;
	
	/**
	 * Creates a new paddle with given width
	 * @param width Width of the paddle
	 */
	public Paddle(double width) {
		/* Sets the Position of the Paddle on the middle of the last row */
		this.Position = new Vector2D(
			((BreakoutConstants.WINDOW_COLUMNS/2d)-2) * BreakoutConstants.WINDOW_WIDTH,
			(BreakoutConstants.WINDOW_ROWS-1) * BreakoutConstants.WINDOW_HEIGHT
		);
		/* Creates a BoundingBox for the paddle */
		this.setBBox(paddleWidth, BreakoutConstants.WINDOW_HEIGHT);
		
		availableWidth = width;
	}
	
	public boolean getReverse(){
		return this.reverse;
	}
	
	/**
	 * Changes the width of the paddle
	 * @param size The value that should be added to the actual width
	 */
	public void changePaddleWidth(double size){
		this.paddleWidth += size;
		this.setBBox(paddleWidth,BreakoutConstants.WINDOW_HEIGHT);
	}
	
	/**
	 * changes the value of the reverse-constant
	 */
	public void toggleReverse(){
		this.reverse=!reverse;
	}
	
	/**
	 * Moves the paddle
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

	public double getWidth(){
	  return this.paddleWidth;
	}

	public Color getColor(){
	  return this.reverse ? new Color(255,182,0) : this.color;
	}
	
	/**
	 * An override of the OnCollision method
	 */
	@Override
	public void onCollision(CollisionEvent e) {
		/* Each CollisionEvent contains the colliding objects. 
		 * x contains the object which is the Ball and we cast it as a Ball.
		 */
		PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
		
		assert x instanceof Ball;
		
		Ball ball = (Ball)x;
		
		//double relativeXPosition = (e.getCollisionPoint().getX() - Position.getX()) / paddleWidth; //from 0 to 1
		
		/* If the Advanced Mechanics are activated the angle in 
		 * which the Ball bounces off depends on the actual paddle-speed
		 */
		if(BreakoutConstants.BALL_BOUNCE_ADVANCED_MECHANICS) {
			/* takes the actual ball speed and depending on the actual paddle-speed calculates the new Ball speed and angle */
			Vector2D vel = new Vector2D(ball.getVelocity());
			double ballspeed2 = vel.sqlength();
			
			vel.setX( vel.getX() + Math.min(8d*speed, 200d));
			
			double f = Math.sqrt(ballspeed2 / vel.sqlength());
			
			vel = vel.scale(f);
			
			/* Minimum vertical speed */
			double minYvel = BreakoutConstants.WINDOW_HEIGHT * 3d;
			
			if(Math.abs(vel.getY()) < minYvel) {
				double sgn = vel.getY() < 0 ? -1.0 : 1.0;
				vel.setY(minYvel * sgn);
				f = Math.sqrt(ballspeed2 / vel.sqlength());
				vel = vel.scale(f);
			}
			
			ball.setVelocity(vel);		
		}
	}
}
