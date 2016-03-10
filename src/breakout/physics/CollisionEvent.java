package breakout.physics;

import breakout.items.Ball;

public class CollisionEvent {
	
	private PhysicsObject a;
	
	private PhysicsObject b;
	
	private Vector2D point;
	
	private boolean horizontal;
	
	/**
	 * Constructor: Creates new CollisionEvent with two given PhysicsObjects, the Collisionpoint and a boolean if it was a horizontal collision
	 * @param a - First PhysicsObject
	 * @param b - Second PhysicsObject
	 * @param point - the point of the collision
	 * @param h - contains if it was a horizontal collision
	 */
	public CollisionEvent(PhysicsObject a, PhysicsObject b, Vector2D point, boolean h) {
		this.a = a;
		this.b = b;
		this.point = point;
		this.horizontal = h;
	}
	
	public PhysicsObject getObjectA() { return a; }
	public PhysicsObject getObjectB() { return b; }
	
	/**
	 * Checks if the collision was a collision with a wall
	 * @return true if the collision contained a wall
	 */
	public boolean isWallCollision() {
		return (a instanceof Ball || b instanceof Ball) && (a == null || b == null);
	}
	
	public boolean isHorizontal() { return horizontal; }
	
	public Vector2D getCollisionPoint() { return point; }
}
