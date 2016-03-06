package breakout.items;

import breakout.assets.BreakoutConstants;
import breakout.physics.Vector2D;
import breakout.physics.PhysicsObject;
import breakout.physics.CollisionEvent;
import java.awt.Color;

public class Brick extends PhysicsObject {
	
	private Color[] color = {new Color(255,0,0), new Color(0,255,0), new Color(0,0,255), new Color(255,0,255),new Color(0,255,255), new Color(255,255,0)};
	private byte brickType;
	private boolean destroyed;
	
	//avoid getter & setter functions:
	public static final double brickWidth = 2 * BreakoutConstants.WINDOW_WIDTH;
	public static final double brickHeight = BreakoutConstants.WINDOW_HEIGHT;
	
	//why spawn a destroyed brick?!
	public Brick(double x, double y, byte brickType, boolean destroy){
		this.Position = new Vector2D(x,y);
		if (!destroy) {setBBox(brickWidth, brickHeight);}
		this.brickType = brickType;
		this.destroyed = destroy;
	}

	public boolean isDestroyed(){
		return this.destroyed;
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
			destroyed = true;
			break;
		//Normal Brick Green	
		case 1:
			destroyed = true;
			break;
		//Normal Brick Blue
		case 2:	
			destroyed = true;
			break;
		//FastBrick, makes the Ball faster	
		case 3:
			PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
			if(x instanceof Ball){
				Ball ball = (Ball)x;
				ball.changeVelocity(1.5);
			}
			destroyed = true;
			break;
		//SlowBrick, makes the Ball slower
		case 4:
			PhysicsObject y = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
			if(y instanceof Ball){
				Ball ball = (Ball)y;
				ball.changeVelocity(-1.5);
			}
			destroyed = true;
			break;
		//ReversePaddleBrick	
		case 5:
			
			destroyed = true;
			break;
		//EnlargePaddle	
		case 6:
			
			destroyed = true;
			break;
		//MinimizePaddle	
		case 7:
			
			destroyed = true;
			break;
		}
		
	}

}
