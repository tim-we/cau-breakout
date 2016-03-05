package breakout.items;

import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;

public class Paddle extends PhysicsObject{

	private double paddleWidth = BreakoutConstants.WINDOW_WIDTH*4;
	
	private double availableWidth = 0;
	
	private Color color = new Color (229,55,203);
	
	private double speed = 0;
	
	public Paddle(double width) {
		this.Position = new Vector2D(
			((BreakoutConstants.WINDOW_COLUMNS/2d)-2) * BreakoutConstants.WINDOW_WIDTH,
			(BreakoutConstants.WINDOW_ROWS-1) * BreakoutConstants.WINDOW_HEIGHT
		);
		
		this.setBBox(paddleWidth, BreakoutConstants.WINDOW_HEIGHT);
		
		availableWidth = width;
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
		
		double relativeXPosition = (e.getCollisionPoint().getX() - Position.getX()) / paddleWidth; //from 0 to 1
		
		if(BreakoutConstants.BALL_BOUNCE_ADVANCED_MACHANICS) {
		
			double angle = Math.PI * 2 * (1-relativeXPosition);
			
			angle = 0.25*Math.PI + angle*0.5;
			
			double speed = ball.getVelocity().length();
			
			ball.setVelocity(new Vector2D(Math.cos(angle), Math.sin(angle)).scale(speed));
			
			
		}
	}
}
