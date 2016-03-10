package breakout.physics;

/**
 * MovingObject inherits a Position from PhysicsObject, saved as Vector2D
 */
public class MovingObject extends PhysicsObject {

	protected Vector2D Velocity = new Vector2D();
	
	public Vector2D getVelocity() { return new Vector2D(Velocity); }
	
	public void setVelocity(Vector2D x) { Velocity = x; }
	
	/**
	 * vertical Bounce, reverses the actual velocity in x direction
	 */
	public void bounceX() { Velocity.setX( -Velocity.getX() ); }
	
	/**
	 * horizontal Bounce, reverses the actual velocity in y direction
	 */
	public void bounceY() { Velocity.setY( -Velocity.getY() ); }
	
	/**
	 * Sets the Position of the Object to a new Position, depending on the 
	 * old Position and the velocity of the Object. The velocity gets added 
	 * to the position after getting scaled by a factor.
	 * @param factor - The factor the velocity gets scaled with
	 */
	public void move(double factor) {
		Position = Vector2D.add(Position, Velocity.scale(factor));
	}
	
	@Override
	public String toString() { return "[Moving Object]"; }
}
