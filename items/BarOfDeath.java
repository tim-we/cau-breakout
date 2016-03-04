package breakout.items;

import breakout.physics.PhysicsObject;

public class BarOfDeath extends PhysicsObject {
	
	public BarOfDeath(double ypos, double width, double height) {
		
		Position.setX(0);
		Position.setY(ypos);
		
		this.setBBox(width, height);		
	}
	
}
