package breakout.items;

import breakout.assets.BreakoutConstants;
import breakout.physics.*;
import java.awt.Color;

public class Ball extends MovingObject {
  
	private double width = BreakoutConstants.WINDOW_WIDTH; 
	private double height = BreakoutConstants.WINDOW_HEIGHT;
	private boolean dead = false;
	public static final double MAX_SPEED_2 = BreakoutConstants.BALL_MAX_SPEED*BreakoutConstants.BALL_MAX_SPEED;
  
	public static final Color color = new Color(255,255,255);
	
	public Ball(Vector2D position) {
		this.Position = position;
		setBBox(width,height);
	}
	
	public Ball(Vector2D position, Vector2D velocity) {
		this.Position = position;
		setBBox(width,height);
		this.Velocity = velocity;
	}
	
	public void setDead(){
		this.dead = true;
	}
	
	public boolean isDead(){
		return this.dead;
	}
	
	public void scaleVelocity(double f) {
		double max_f = Math.sqrt(MAX_SPEED_2 / Velocity.sqlength());
		
		f = Math.min(Math.max(-max_f, f), max_f);
		
		Velocity = Velocity.scale(f);
		
		//System.out.println("Velocity^2: " + Velocity.sqlength());
	}
	
	public void changeVelocity(double amount){
		double speed = Velocity.length();
		double f = (speed + amount) / speed;
		
		scaleVelocity(f);
	}
	

}
