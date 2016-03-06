package breakout.items;
import breakout.assets.BreakoutConstants;
import breakout.physics.*;
import java.awt.Color;
import breakout.assets.*;
public class Ball extends MovingObject {
  
	private double width = BreakoutConstants.WINDOW_WIDTH; 
	private double height = BreakoutConstants.WINDOW_HEIGHT;
	
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
	
	public Color getColor() {
		return this.color;
	}
	
	public void scaleVelocity(double f) {
		double max_f = Math.sqrt(MAX_SPEED_2 / Velocity.sqlength());
		
		f = Math.min(Math.max(-max_f, f), max_f);
		
		Velocity = Velocity.scale(f);
		
		//System.out.println("Velocity^2: " + Velocity.sqlength());
	}
	
	public void changeVelocity(double f){
		this.Velocity.setX(this.Velocity.getX()+f*BreakoutConstants.BALL_VELOCITY_CHANGE);
		this.Velocity.setY(this.Velocity.getY()+f*BreakoutConstants.BALL_VELOCITY_CHANGE);
	}
	

}
