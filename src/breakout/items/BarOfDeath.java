package breakout.items;

import breakout.physics.PhysicsObject;

public class BarOfDeath extends PhysicsObject {
	
	/**
	 * Creates a new BarOfDeath at given Position with height and width
	 * @param ypos Y-Position of the BarOfDeath
	 * @param width Width of the BarOfDeath
	 * @param height Height of the BarOfDeath
	 */
	public BarOfDeath(double ypos, double width, double height) {
		
		Position.setX(0);
		Position.setY(ypos);
		
		this.setBBox(width, height);		
	}
	
}
