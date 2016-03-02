package breakout.items;
import breakout.physics.*;
import java.awt.Color;

public class Ball extends MovingObject {
  
  private double width = 25; 
  private double height = 50;

	private Color color = new Color(255,0,0);
	
	public Ball(Vector2D position) {
	  this.Position = position;
	  setBBox(width,height);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void scaleVelocity(double f) {
		Velocity = Velocity.scale(f);
	}

}
