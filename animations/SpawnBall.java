package breakout.animations;

import breakout.main.Model;
import breakout.main.View;
import breakout.physics.Vector2D;

public class SpawnBall extends Animation {
	
	private double xpos;
	private double ypos;
	
	public SpawnBall(Vector2D pos, Model m, View v) {
		int[] tmp = v.getViewCoordinates(pos, m);
		
		xpos = tmp[0];
		ypos = tmp[1];
	}
	
}
