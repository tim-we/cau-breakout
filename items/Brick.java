package breakout.items;

import breakout.assets.BreakoutConstants;
import breakout.physics.Vector2D;
import breakout.physics.PhysicsObject;
import breakout.physics.CollisionEvent;
import java.awt.Color;

public class Brick extends PhysicsObject {
	
	private Color[] color = {new Color(255,0,0), new Color(0,255,0), new Color(0,0,255), new Color(255,0,255),new Color(0,255,255), new Color(255,255,0)};
	private byte brickType;
	
	//avoid getter & setter functions:
	public static final double brickWidth = 2 * BreakoutConstants.WINDOW_WIDTH;
	public static final double brickHeight = BreakoutConstants.WINDOW_HEIGHT;

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

	public Color getColor() {
		if(brickType<0) { return new Color(0,0,0,0); }
		return this.color[brickType % color.length];
	}
	
	public int getHitPoints() {
		return 42;
	}

	@Override
	public void onCollision(CollisionEvent e){
	  
		switch (brickType){
		//Normal Brick Red
		case 0:
			brickType -= 1;
			break;
		//Normal Brick Green	
		case 1:
			brickType -= 2;
			break;
		//Normal Brick Blue
		case 2:	
			brickType -= 3;
			break;
		//FastBrick, makes the Ball faster	
		case 3:
			PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
			if(x instanceof Ball){
				Ball ball = (Ball)x;
				ball.changeVelocity(1.5);
			}
			brickType -= 4;
			break;
		//SlowBrick, makes the Ball slower
		case 4:
			PhysicsObject y = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
			if(y instanceof Ball){
				Ball ball = (Ball)y;
				ball.changeVelocity(-1.5);
			}
			brickType -= 5;
			break;
		//	
		case 5:
			
			brickType -= 6;
			break;
		//	
		case 6:
			
			brickType -= 7;
			break;
		
		}
		
	}

}
