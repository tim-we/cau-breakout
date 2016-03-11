package breakout.items;

import breakout.main.Config;
import breakout.physics.Vector2D;
import breakout.physics.PhysicsObject;
import breakout.physics.CollisionEvent;

/* available brick types:
 * 0,1,2 are default bricks
 * 3 => FastBrick, which makes the ball faster
 * 4 => SlowBrick, which makes the ball slower
 * 5 => ReverseBrick, which reverses the paddle control
 * 6 => EnlargeBrick, which makes the paddle larger
 * 7 => ReduceBrick, which makes the paddle smaller
 * 8 => BallBrick, which adds another Ball
 * 9 => RandomBrick, which performs a random action
 * */

public class Brick extends PhysicsObject {
	
	private byte brickType;
	private boolean destroyed;
	
	//avoid getter & setter functions:
	public static final double brickWidth = 2 * Config.WINDOW_WIDTH;
	public static final double brickHeight = Config.WINDOW_HEIGHT;
	
	public Brick(double x, double y, byte brickType){
		this.Position = new Vector2D(x,y);
		setBBox(brickWidth, brickHeight);
		this.brickType = brickType;
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
	
	public int getHitPoints() {
		return getHitPoints(1d);
	}
	
	public int getHitPoints(double multiplier) {
		return (int)Math.round(42 * multiplier);
	}	
	
	/**
	 * collision event listener
	 */
	@Override
	public void onCollision(CollisionEvent e){
		/* brick gets destroyed */
		destroyed = true;
		
		switch (brickType){	
			case 3: //FastBrick, makes the Ball faster
				PhysicsObject x = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
				if(x instanceof Ball){
					Ball ball = (Ball)x;
					ball.changeVelocity(Config.BALL_VELOCITY_CHANGE);
				}
				break;
			
			case 4: //SlowBrick, makes the Ball slower
				PhysicsObject y = e.getObjectA() instanceof Ball ? e.getObjectA() : e.getObjectB();
				if(y instanceof Ball){
					Ball ball = (Ball)y;
					ball.changeVelocity(-0.7*Config.BALL_VELOCITY_CHANGE);
				}
				break;
		}		
	}
	
	/**
	 * Returns the BrickType if not destroyed as a String
	 */
	@Override
	public String toString() {
		return destroyed ? "[destroyed Brick]":"[Brick Type: "+brickType+"]";
	}
	
}
