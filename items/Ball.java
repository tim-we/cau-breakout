package breakout.items;
import breakout.physics.*;
import java.awt.Color;

public class Ball extends MovingObject {
  
  private double width = 25; 
  private double height = 50;
  
	private Color color = new Color(255,255,255);
	
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
		Velocity = Velocity.scale(f);
	}


}
