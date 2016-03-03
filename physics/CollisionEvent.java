package breakout.physics;

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
	
	public Vector2D getCollisionPoint() { return point; }
}
