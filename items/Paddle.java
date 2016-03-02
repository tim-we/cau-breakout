package breakout.items;
import breakout.assets.*;
import breakout.physics.*;
import java.awt.Color;
public class Paddle extends PhysicsObject{

	private double paddleWidth = 4*PixelSizes.PIXELWIDTH;
	
	private Color color = new Color (142,33,125);
	
	public Paddle(){
	  this.Position = new Vector2D(((28/2)-2)*PixelSizes.PIXELWIDTH,13*PixelSizes.PIXELHEIGHT);
	}
	
	public void setPosition (double x){
	  Position.setX(x);
	}

	public double getWidth(){
	  return this.paddleWidth;
	}

	public Color getColor(){
	  return this.color;
	}

}
