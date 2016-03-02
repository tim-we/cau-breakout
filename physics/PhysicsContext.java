package breakout.physics;

import java.util.ArrayList;

public class PhysicsContext {
	
	private double upperBound = 0d;
	private double lowerBound;
	private double leftBound = 0d;
	private double rightBound;
	
	public ArrayList<PhysicsObject> staticObjects;
	
	public PhysicsContext(double width, double height) {
		lowerBound = height;
		rightBound = width;
		
		staticObjects = new ArrayList<PhysicsObject>();
	}
	
	/** public for debugging!
	 * @param a - start point of the first line segment
	 * @param b - vector from start to end point of the first line segment
	 * @param c - start point of the second line segment
	 * @param d - vector from start to end point of the second line segment
	 * @return null (no intersection) vector containing factors
	 */
	public static Vector2D lineSegmentIntersection(Vector2D a, Vector2D b, Vector2D c, Vector2D d) {	
		
		double detA = b.getY()*d.getX() - b.getX()*d.getY();
		
		if(detA == 0) { System.out.println("! det = 0"); return null; }
		//else { System.out.println("det = " + detA); }
		
		//Cramer's rule (2x2 matrix, 2D vector space):		
		double detA1 = (c.getY()-a.getY()) * d.getX() - (c.getX()-a.getX()) * d.getY();
		double detA2 = b.getX() * (c.getY() - a.getY()) - b.getY() * (c.getX() - a.getX());
		
		//solutions
		double x1 = detA1/detA;
		double x2 = detA2/detA;
		
		if(0d<=x1 && x1<=1d && 0d<=x2 && x2<=1d) {
			//line segments intersect
			return new Vector2D(x1, x2);
		}
		else {
			//segments do not intersect (but as infinite lines they would cross)
			return null;
		}
	}
	
	/**
	 * @param obj
	 * @param deltaTime (1 = 1 second)
	 * @return
	 */
	public double updateObject(MovingObject obj, double deltaTime) {
		
		if(obj.getVelocity().sqlength() == 0) {
			//not moving
			return 0d;
		}
		
		Vector2D vel = obj.getVelocity().scale(deltaTime);
		Vector2D result;
		Vector2D[] bboxA = obj.getBoundingBox();
		
		double minFactor = 2d;
		boolean horizontal = true;
		PhysicsObject collisionObject = null;
		
		for(PhysicsObject statObj : staticObjects) {
			
			Vector2D[] bboxB = statObj.getBoundingBox();

			for(int i=0; i<bboxA.length; i++) {
				if(vel.getX() != 0) { //horizontal collision
					
					if(vel.getX() > 0) {
						result = lineSegmentIntersection(bboxA[i], vel, bboxB[0], Vector2D.subtract(bboxB[2], bboxB[0]));
					} else {
						result = lineSegmentIntersection(bboxA[i], vel, bboxB[1], Vector2D.subtract(bboxB[3], bboxB[1]));	
					}
					
					if(result != null) {
						
						if(result.getX() < minFactor) {
							minFactor = result.getX();
							horizontal = true;
							collisionObject = statObj;
						}
					}
				} //end horizontal collision
							
				if(vel.getY() != 0) { //vertical collision
					if(vel.getY() > 0) {
						result = lineSegmentIntersection(bboxA[i], vel, bboxB[0], Vector2D.subtract(bboxB[1], bboxB[0]));
					} else {
						result = lineSegmentIntersection(bboxA[i], vel, bboxB[2], Vector2D.subtract(bboxB[3], bboxB[2]));
					}
					
					if(result != null) {
						
						if(result.getX() < minFactor) {
							minFactor = result.getX();
							horizontal = false;
							collisionObject = statObj;
						}
					}
				} //end vertical collision
			} //end-for
		}
		
		//wall collision
		if(minFactor > 1d) { //assuming all objects are within the bounds
			
			for(int i=0; i<bboxA.length; i++) {
				
				if(vel.getX() != 0) { //horizontal collision
					
					if(vel.getX() > 0) {
						result = lineSegmentIntersection(bboxA[i], vel, new Vector2D(rightBound, upperBound), new Vector2D(0, lowerBound-upperBound));
					} else {
						result = lineSegmentIntersection(bboxA[i], vel, new Vector2D(leftBound, upperBound), new Vector2D(0, lowerBound-upperBound));
					}
					
					if(result != null) {
						
						if(result.getX() < minFactor) {
							minFactor = result.getX();
							horizontal = true;
						}
					}
				} //end horizontal collision
							
				if(vel.getY() != 0) { //vertical collision
					if(vel.getY() > 0) {
						result = lineSegmentIntersection(bboxA[i], vel, new Vector2D(leftBound, lowerBound), new Vector2D(rightBound-leftBound, 0));
					} else {
						result = lineSegmentIntersection(bboxA[i], vel, new Vector2D(leftBound, upperBound), new Vector2D(rightBound-leftBound, 0));		
					}
					
					if(result != null) {
						
						if(result.getX() < minFactor) {
							minFactor = result.getX();
							horizontal = false;
							
						}
					}
				} //end vertical collision
			} //end-for
		}
	
		if(minFactor <= 1d) {
			//collision!
			
			//move
				obj.move(minFactor * deltaTime);
				
				if(horizontal) { obj.bounceX(); }
				else { obj.bounceY(); }
				
			//trigger collision event
				obj.onCollision(collisionObject);
				if(collisionObject != null) { collisionObject.onCollision(obj); }
			
			return 1d-minFactor;
		} else {			
			obj.move(deltaTime);
		}
		
		return 0d;
	}
	
}
