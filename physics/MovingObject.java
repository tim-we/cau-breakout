package breakout.physics;

public class MovingObject extends PhysicsObject {

	protected Vector2D Velocity = new Vector2D();
	
	public Vector2D getVelocity() { return new Vector2D(Velocity); }
	
	public void setVelocity(Vector2D x) { Velocity = x; }
	
	public void bounceX() { Velocity.setX( -Velocity.getX() ); }
	
	public void bounceY() { Velocity.setY( -Velocity.getY() ); }
	
	public void move(double factor) {
		Position = Vector2D.add(Position, Velocity.scale(factor));
	}
	
}
