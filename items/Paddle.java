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
	
	public Paddle(double width) {
		this.Position = new Vector2D(
			((BreakoutConstants.WINDOW_COLUMNS/2d)-2) * BreakoutConstants.WINDOW_WIDTH,
			(BreakoutConstants.WINDOW_ROWS-1) * BreakoutConstants.WINDOW_HEIGHT
		);
		
		this.setBBox(paddleWidth, BreakoutConstants.WINDOW_HEIGHT);
		
		availableWidth = width;
	}
	
	public boolean getReverse(){
		return this.reverse;
	}
	
	public void changePaddleWidth(double size){
		this.paddleWidth += size;
		this.setBBox(paddleWidth,BreakoutConstants.WINDOW_HEIGHT);
	}
	
	public void toggleReverse(){
		this.reverse=!reverse;
	}
	
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
	  return this.color;
	}
	
	@Override
	public void onCollision(CollisionEvent e) {
		PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
		
		assert x instanceof Ball;
		
		Ball ball = (Ball)x;
		
		//double relativeXPosition = (e.getCollisionPoint().getX() - Position.getX()) / paddleWidth; //from 0 to 1
		
		if(BreakoutConstants.BALL_BOUNCE_ADVANCED_MECHANICS) {
			
			Vector2D vel = new Vector2D(ball.getVelocity());
			double ballspeed2 = vel.sqlength();
			
			//System.out.println("ball speed: " + vel.length() + " paddle speed: " + speed);
			
			vel.setX( vel.getX() + Math.min(8d*speed, 200d));
			
			double f = Math.sqrt(ballspeed2 / vel.sqlength());
			
			vel = vel.scale(f);
			
			ball.setVelocity(vel);		
		}
	}
}
