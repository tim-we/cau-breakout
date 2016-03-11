package breakout.items;

import breakout.main.Config;
import breakout.physics.*;

public class Ball extends MovingObject {
	
	private double width = Config.WINDOW_WIDTH; 
	private double height = Config.WINDOW_HEIGHT;
	
	private boolean dead = false;
	
	public static final double MAX_SPEED_2 = Config.BALL_MAX_SPEED*Config.BALL_MAX_SPEED;
	
	private  Vector2D[] Tail = new Vector2D[Config.TAIL_LENGTH];
	
	private double moved_f = 0;
	
	private Vector2D lastPos;
	
	/**
	 * Creates a new Ball with given Position
	 * @param position the Position of the Ball
	 */
	public Ball(Vector2D position) {
		this.Position = position;
		lastPos = position;
		setBBox(width,height);
	}
	
	/**
	 * Creates a new Ball with given Position and Velocity
	 * @param position the Position of the Ball
	 * @param velocity the Velocity of the Ball
	 */
	public Ball(Vector2D position, Vector2D velocity) {
		this.Position = position;
		lastPos = position;
		setBBox(width,height);
		this.Velocity = velocity;
	}
	
	public void setDead() {
		this.dead = true;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	/**
	 * Scales the Velocity of the Ball with a given value
	 * @param f the value to scale the velocity with
	 */
	public void scaleVelocity(double f) {
		double max_f = Math.sqrt(MAX_SPEED_2 / Velocity.sqlength());
		
		f = Math.min(Math.max(-max_f, f), max_f);
		
		Velocity = Velocity.scale(f);
	}
	
	/**
	 * Changes the Velocity with a given amount
	 * @param amount the value to add to the actual velocity
	 */
	public void changeVelocity(double amount){
		double speed = Velocity.length();
		double f = (speed + amount) / speed;
		
		scaleVelocity(f);
	}
	
	/**
	 * Moves the ball by {factor} times the ball velocity.
	 * This might be controller-logic but in our opinion it 
	 * would not make sense to put it anywhere but here
	 * because of the underlying MovingObject class. 
	 */
	@Override
	public void move(double factor) {
		moved_f += factor;
		
		double h = 1.0/Config.FPS;
		
		if(moved_f >= h) {
			/* The tail of the Ball gets a new start position and the rest of the tail gets shifted */
			for(int i=Tail.length-1; i>0; i--) {
				Tail[i] = Tail[i-1];
			}
			Tail[0] = new Vector2D(lastPos);
			lastPos = new Vector2D(Position);
			moved_f = 0;
		}
		
		Position = Vector2D.add(Position, Velocity.scale(factor));
	}
	
	public Vector2D[] getTail() {
		return Tail;
	}
}
