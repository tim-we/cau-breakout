package breakout.items;

import breakout.assets.PixelSizes;
import breakout.physics.Vector2D;
import breakout.physics.PhysicsObject;
import java.awt.Color;

public class Brick extends PhysicsObject {
	
	private Color[] color = {new Color(255,0,0),new Color(0,255,0),new Color(0,0,255)};
	private byte brickType;
	
	//avoid getter & setter functions:
	public static final double brickWidth = 2*PixelSizes.PIXELWIDTH;
	public static final double brickHeight = PixelSizes.PIXELHEIGHT;

	public Brick(double x, double y, byte brickType){
		this.Position = new Vector2D(x,y);
		setBBox(brickWidth, brickHeight);
		this.brickType = brickType;
	}

	public void setBrickType(byte brickType){
	  this.brickType = brickType;
	}

	public byte getBrickType() {
	  return this.brickType;
	}

	public Color getColor(){
	  return this.color[brickType];
	}

	@Override
	public void onCollision(PhysicsObject obj){
	  brickType--;
	}

}
