package breakout.items;
import breakout.assets.*;
import breakout.physics.*;

public class Paddle extends PhysicsObject{

private double paddleWidth = 4*PixelSizes.PIXELWIDTH;

	public Paddle(){
	  this.Position = new Vector2D(((28/2)-2)*PixelSizes.PIXELWIDTH,13*PixelSizes.PIXELHEIGHT);
	}
	
	public void setPosition (double x){
	  Position.setX(x);
	}

}
