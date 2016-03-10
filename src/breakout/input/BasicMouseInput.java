package breakout.input;

import java.awt.MouseInfo;

import breakout.items.Paddle;
import breakout.main.Model;

public class BasicMouseInput implements BreakoutInput {
	
	private int lastMouseXPos = 0;
	
	private double mouseSpeed = 0.8;
	
	@Override
	public void update(Paddle paddle, int ms) {
		/* Saves the new Mouse Position and moves the Paddle 
		 * with the difference to the last Mouse Position */
		int newPosition = MouseInfo.getPointerInfo().getLocation().x;
		
		double d = mouseSpeed * (newPosition-lastMouseXPos);
		
		paddle.move (d);
		lastMouseXPos = newPosition;
		
	}

	public void init(Model m) {}
	
}
