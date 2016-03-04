package breakout.items;

import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;

public class Paddle extends PhysicsObject{

	private double paddleWidth = BreakoutConstants.WINDOW_WIDTH*4;
	
	private Color color = new Color (229,55,203);
	
	public Paddle(){
		this.Position = new Vector2D(
			((BreakoutConstants.WINDOW_COLUMNS/2d)-2) * BreakoutConstants.WINDOW_WIDTH,
			(BreakoutConstants.WINDOW_ROWS-1) * BreakoutConstants.WINDOW_HEIGHT
		);
		
		this.setBBox(paddleWidth, BreakoutConstants.WINDOW_HEIGHT);
	}
	

	
	public void setPosition (double x){
		//TODO: check if x>=0 && x<=worldWidth-paddleWidth
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
		
		double relativeXPosition = -2*(((e.getCollisionPoint().getX() - Position.getX()) / paddleWidth) - 1.0); //from 0 to 1
		
		if(BreakoutConstants.BALL_BOUNCE_ADVANCED_MACHANICS) {
		
			double angle = Math.PI * 2 * (1-relativeXPosition);
			
			angle = 0.25*Math.PI + angle*0.5;
			
			double speed = ball.getVelocity().length();
			
			ball.setVelocity(new Vector2D(Math.cos(angle), Math.sin(angle)).scale(speed));
		}
	}
}
