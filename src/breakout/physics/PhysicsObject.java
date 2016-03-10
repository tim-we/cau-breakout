package breakout.physics;

public abstract class PhysicsObject {
	
	//top-left oriented
	protected Vector2D Position = new Vector2D();
	
	//(bounding box) bottom-right corner (relative position)
	protected Vector2D BBoxPoint = new Vector2D();
	
	/**
	 * Sets the bounding box of the object to a vector which contains x and y
	 * @param x - the x value of the vector 
	 * @param y - the y value of the vector
	 * @return true if the bounding box was set, false if x or y are out of range (<0)
	 */
	protected boolean setBBox(double x, double y) { //public or protected?
		if(x>=0 && y>=0) {
			BBoxPoint = new Vector2D(x,y);
			
			return true;
		}
		
		return false;
	}
	
	public Vector2D getPosition() { return Position; }
	
	public Vector2D getBBoxPoint() { return BBoxPoint; }
	
	/**
	 * Gives the coordinates of the corners of the bounding box
	 * @return a Vector Array which contains the corners
	 */
	public Vector2D[] getBoundingBox() {
		Vector2D[] bbox = new Vector2D[4];
		
		bbox[0] = new Vector2D(Position); //top-left
		bbox[1] = Vector2D.add(Position, new Vector2D(BBoxPoint.getX(), 0)); //top-right
		bbox[2] = Vector2D.add(Position, new Vector2D(0, BBoxPoint.getY())); //bottom-left
		bbox[3] = Vector2D.add(Position, BBoxPoint); //bottom-right
		
		return bbox;
	}
	
	/**
	 * Sets the position
	 * @param pos - the new Position
	 */
	public void updatePosition(Vector2D pos) {
		Position = pos;
	}
	
	public void onCollision(CollisionEvent e) {}
	
	public String toString() { return "[PhysicsObject]"; }
}
