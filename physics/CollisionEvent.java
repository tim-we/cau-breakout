package breakout.physics;

import breakout.items.Ball;

public class CollisionEvent {
	
	private PhysicsObject a;
	
	private PhysicsObject b;
	
	private Vector2D point;
	
	public CollisionEvent(PhysicsObject a, PhysicsObject b, Vector2D point) {
		this.a = a;
		this.b = b;
		this.point = point;
	}
	
	public PhysicsObject getObjectA() { return a; }
	public PhysicsObject getObjectB() { return b; }
	
	public boolean isWallCollision() {
		return (a instanceof Ball || b instanceof Ball) && (a == null || b == null);
	}
	
	public Vector2D getCollisionPoint() { return point; }
}
